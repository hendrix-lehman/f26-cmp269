
plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(ktorLibs.plugins.ktor)
    kotlin("plugin.serialization") version "2.1.0" // <-- Must match kotlin("jvm") version!
}

group = "com.example"
version = "1.0.0-SNAPSHOT"

application {
  mainClass.set("com.example.MainKt")
}

kotlin {
    jvmToolchain(21)
}
dependencies {
    implementation(ktorLibs.server.config.yaml)
    implementation(ktorLibs.server.core)
    implementation(ktorLibs.server.netty)
    implementation(libs.logback.classic)

        
    // Content Negotiation & JSON
    implementation("io.ktor:ktor-server-content-negotiation-jvm:3.0.3")
    implementation("io.ktor:ktor-serialization-kotlinx-json-jvm:3.0.3")
    
    // kotlinx.serialization runtime library
        // Explicit kotlinx.serialization runtime library
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.7.3")

    testImplementation(kotlin("test"))
    testImplementation(ktorLibs.server.testHost)
}
