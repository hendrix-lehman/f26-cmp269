package com.example

import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.request.*
import io.ktor.server.routing.*
import io.ktor.http.*
import kotlinx.serialization.Serializable
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.plugins.contentnegotiation.*

// 1. DATA MODEL (Serialization)
// @Serializable instructs the compiler to generate JSON <-> Kotlin converters.
@Serializable
data class Task(
    val id: Int? = null,
    val title: String,
    val completed: Boolean = false
)

// In-memory database for demonstration
val taskDatabase = mutableListOf(
    Task(id = 1, title = "Learn HTTP Verbs", completed = true),
    Task(id = 2, title = "Build a Ktor Server", completed = false)
)

fun main(args: Array<String>) {
    embeddedServer(Netty, port = 8081) {
      configurePlugins()
      configureRouting()
    }.start(wait = true)
}

fun Application.configurePlugins() {
    // 3. CONTENT NEGOTIATION
    // Intercepts incoming requests (Content-Type: application/json) and converts JSON -> Task.
    // Intercepts outgoing responses (Accept: application/json) and converts Task -> JSON.
    install(ContentNegotiation) {
        json()
    }
}

fun Application.configureRouting() {
    // 4. ROUTING DSL & REQUEST/RESPONSE LIFECYCLE
    routing {
        // Root health check
        get("/") {
            call.respondText("Ktor Server is running on Netty!", ContentType.Text.Plain)
        }

        // Group related endpoints under /tasks
        route("/tasks") {

            // HTTP GET: Read all resources -> Returns 200 OK
            get {
                println("[LOG] GET /tasks -> Returning ${taskDatabase.size} tasks")
                call.respond(HttpStatusCode.OK, taskDatabase)
            }

            // HTTP GET with Path Parameter: Read one resource -> Returns 200 OK or 404 Not Found
            get("/{id}") {
                val id = call.parameters["id"]?.toIntOrNull()
                if (id == null) {
                    // 400 Bad Request: Client sent invalid input
                    call.respond(HttpStatusCode.BadRequest, mapOf("error" to "ID must be a number"))
                    return@get
                }

                val task = taskDatabase.find { it.id == id }
                if (task == null) {
                    // 404 Not Found: Valid request, but resource doesn't exist
                    call.respond(HttpStatusCode.NotFound, mapOf("error" to "Task $id not found"))
                } else {
                    // 200 OK: Resource found and serialized to JSON
                    call.respond(HttpStatusCode.OK, task)
                }
            }

            // HTTP POST: Create a new resource -> Returns 201 Created
            post {
                // ContentNegotiation automatically parses the JSON body into a Task object
                val incomingTask = call.receive<Task>()
                val newId = (taskDatabase.maxOfOrNull { it.id ?: 0 } ?: 0) + 1
                val savedTask = incomingTask.copy(id = newId)

                taskDatabase.add(savedTask)
                println("[LOG] POST /tasks -> Created Task #$newId")

                // 201 Created: Standard status code when a new record is created
                call.respond(HttpStatusCode.Created, savedTask)
            }

            // HTTP DELETE: Remove a resource -> Returns 204 No Content or 404 Not Found
            delete("/{id}") {
                val id = call.parameters["id"]?.toIntOrNull()
                val removed = taskDatabase.removeIf { it.id == id }

                if (removed) {
                    println("[LOG] DELETE /tasks/$id -> Deleted")
                    // 204 No Content: Success, nothing left to send back in body
                    call.respond(HttpStatusCode.NoContent)
                } else {
                    call.respond(HttpStatusCode.NotFound, mapOf("error" to "Task not found"))
                }
            }
        }
    }
}

