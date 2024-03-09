package nl.avtolz.plugins

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import nl.avtolz.quickq.routing.quickqRouting
import nl.avtolz.x32.routing.x32Routing

fun Application.configureRouting() {
    routing {
        quickqRouting()
        x32Routing()

        get("/") {
            call.respondText("Hello World!")
        }
    }
}
