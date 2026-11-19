package com.example

import com.google.zxing.BarcodeFormat
import com.google.zxing.EncodeHintType
import com.google.zxing.client.j2se.MatrixToImageWriter
import com.google.zxing.common.BitMatrix
import com.google.zxing.qrcode.QRCodeWriter
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.Serializable
import java.io.ByteArrayOutputStream

// Structured JSON model for clean error responses
@Serializable
data class ErrorResponse(
    val status: Int,
    val error: String,
    val message: String
)

fun main() {
    embeddedServer(Netty, port = 8081, host = "0.0.0.0") {
        configurePlugins()
        configureRouting()
    }.start(wait = true)
}

fun Application.configurePlugins() {
    install(ContentNegotiation) {
        json()
    }

    // =========================================================================
    // 1. STATUS PAGES: GLOBAL ERROR INTERCEPTION
    // =========================================================================
    // Catches exceptions thrown anywhere in our routes and converts them into
    // clean, predictable HTTP status codes + JSON bodies instead of stack traces.
    install(StatusPages) {
        // Handle bad client inputs (e.g., missing text, invalid size, bad EC level)
        exception<IllegalArgumentException> { call, cause ->
            call.respond(
                HttpStatusCode.BadRequest,
                ErrorResponse(
                    status = 400,
                    error = "Bad Request",
                    message = cause.message ?: "Invalid parameter provided."
                )
            )
        }

        // Catch-all for any unexpected server crashes (500 Internal Server Error)
        exception<Throwable> { call, cause ->
            call.respond(
                HttpStatusCode.InternalServerError,
                ErrorResponse(
                    status = 500,
                    error = "Internal Server Error",
                    message = "An unexpected error occurred: ${cause.localizedMessage}"
                )
            )
        }
    }
}

fun Application.configureRouting() {
    routing {

       get("/") {                                                            
            val htmlContent = """                                             
                <!DOCTYPE html>                                               
                <html>                                                        
                <head><title>CMP 269 QR Service</title></head>                
                <body style="font-family:sans-serif; text-align:center;       
  padding:50px;">                                                             
                    <h1>Lehman College QR Code Generator</h1>                 
                    <input type="text" id="urlInput" placeholder="Enter URL or
  Text..." style="padding:10px; width:300px;">                                
                    <button onclick="generateQR()" style="padding:10px        
  20px;">Generate QR</button>                                                 
                    <div style="margin-top:30px;">                            
                        <img id="qrDisplay" src="" style="display:none;       
  border:2px solid #333;">                                                    
                    </div>                                                    
                    <script>                                                  
                        function generateQR() {                               
                            const txt =                                       
  encodeURIComponent(document.getElementById('urlInput').value);              
                            if (!txt) return;                                 
                            const img = document.getElementById('qrDisplay'); 
                            img.src = '/api/qr?text=' + txt + '&size=300';    
                            img.style.display = 'inline-block';               
                        }                                                     
                    </script>                                                 
                </body>                                                       
                </html>                                                       
            """.trimIndent()                                                  
                                                                              
            call.respondText(htmlContent, ContentType.Text.Html)              
        } 

        // Endpoint 1: Streams a binary PNG image directly to the client
        // Example: GET /qr?text=HelloKtor&size=300&ec=H
        get("/api/qr") {
            // 1. Extract and validate query parameters
            // Notice: require() throws IllegalArgumentException, which StatusPages intercepts!
            val text = call.request.queryParameters["text"]
            require(!text.isNullOrBlank()) { "Query parameter 'text' is required and cannot be empty." }

            val size = call.request.queryParameters["size"]?.toIntOrNull() ?: 250
            require(size in 50..1000) { "Parameter 'size' must be a number between 50 and 1000 pixels." }

            // Reed-Solomon Error Correction Level: L (7%), M (15%), Q (25%), H (30%)
            val ecParam = call.request.queryParameters["ec"]?.uppercase() ?: "M"
            val ecLevel = when (ecParam) {
                "L" -> ErrorCorrectionLevel.L
                "M" -> ErrorCorrectionLevel.M
                "Q" -> ErrorCorrectionLevel.Q
                "H" -> ErrorCorrectionLevel.H
                else -> throw IllegalArgumentException("Invalid 'ec' level '$ecParam'. Allowed values: L, M, Q, H.")
            }

            // 2. Generate the 2D Boolean Matrix via ZXing
            val bitMatrix: BitMatrix = generateQrMatrix(text, size, ecLevel)

            // 3. Convert the 2D Matrix into raw PNG bytes in memory
            val pngStream = ByteArrayOutputStream()
            MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngStream)
            val pngBytes: ByteArray = pngStream.toByteArray()

            // 4. Stream binary bytes with Content-Type: image/png
            call.respondBytes(
                bytes = pngBytes,
                contentType = ContentType.Image.PNG,
                status = HttpStatusCode.OK
            )
        }

        // Endpoint 2: Teaching Demo - Prints the raw 2D QR BitMatrix as ASCII art
        // Great for showing students the 3 finder patterns and data modules!
        get("/api/qr/ascii") {
            val text = call.request.queryParameters["text"]
            require(!text.isNullOrBlank()) { "Query parameter 'text' is required." }

            // Passing size = 0 forces ZXing to return the raw, unscaled module matrix (e.g., 21x21)
            val rawMatrix = generateQrMatrix(text, size = 0, ErrorCorrectionLevel.L)
            
            val asciiBuilder = StringBuilder()
            asciiBuilder.appendLine("QR Matrix Dimensions: ${rawMatrix.width}x${rawMatrix.height} modules\n")
            
            for (y in 0 until rawMatrix.height) {
                for (x in 0 until rawMatrix.width) {
                    // true = dark module (1), false = light module (0)
                    asciiBuilder.append(if (rawMatrix.get(x, y)) "██" else "  ")
                }
                asciiBuilder.appendLine()
            }

            call.respondText(asciiBuilder.toString(), ContentType.Text.Plain)
        }
    }
}

// =========================================================================
// 2 & 3. 2D QR MATRIX THEORY (REED-SOLOMON) & ZXING ENCODING
// =========================================================================
fun generateQrMatrix(content: String, size: Int, ecLevel: ErrorCorrectionLevel): BitMatrix {
    val hints = mapOf(
        // Sets Reed-Solomon redundancy level (H allows up to 30% of the QR code to be damaged/covered)
        EncodeHintType.ERROR_CORRECTION to ecLevel,
        EncodeHintType.CHARACTER_SET to "UTF-8",
        EncodeHintType.MARGIN to 2 // Quiet zone border around the matrix
    )

    val writer = QRCodeWriter()
    return writer.encode(content, BarcodeFormat.QR_CODE, size, size, hints)
}

