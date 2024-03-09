package nl.avtolz

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import nl.avtolz.plugins.*

const val QUICKQ_IP = "localhost"
const val QUICKQ_PORT = 12345
const val X32_IP = "localhost"
const val X32_PORT = 12345

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    configureSecurity()
    configureSerialization()
    configureRouting()
}
