package nl.avtolz.plugins

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import nl.avtolz.quickq.routing.feedbackRouting
import nl.avtolz.quickq.routing.playbackRouting
import nl.avtolz.quickq.routing.tenSceneRouting

fun Application.configureRouting() {
    routing {
        playbackRouting()
        tenSceneRouting()
        feedbackRouting()

        get("/") {
            call.respondText("Hello World!")
        }
    }
}
