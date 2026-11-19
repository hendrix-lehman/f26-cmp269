plugins {
  alias(libs.plugins.kotlin.jvm)
  alias(ktorLibs.plugins.ktor)
  kotlin("plugin.serialization") version "2.1.0"
}

group = "com.example"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    val ktorVersion = "3.0.3"
    
    // Ktor Core, Netty & JSON
    implementation("io.ktor:ktor-server-core-jvm:$ktorVersion")
    implementation("io.ktor:ktor-server-netty-jvm:$ktorVersion")
    implementation("io.ktor:ktor-server-content-negotiation-jvm:$ktorVersion")
    implementation("io.ktor:ktor-serialization-kotlinx-json-jvm:$ktorVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.7.3")
    
    // 1. NEW: StatusPages for Global Error Interception
    implementation("io.ktor:ktor-server-status-pages-jvm:$ktorVersion")
    
    // 2. NEW: Google ZXing (Zebra Crossing) for QR Matrix & Image Generation
    implementation("com.google.zxing:core:3.5.3")
    implementation("com.google.zxing:javase:3.5.3")
    
    // Logger
    implementation("ch.qos.logback:logback-classic:1.5.12")
}

application {
    mainClass.set("com.example.ApplicationKt")
}

kotlin {
  jvmToolchain(21)
}
