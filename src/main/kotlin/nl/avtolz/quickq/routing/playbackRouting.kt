package nl.avtolz.quickq.routing

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.Serializable
import nl.avtolz.quickq.commands.Playback

@Serializable
data class PBArguments(val level: Int)

fun Routing.playbackRouting() {
    val playbackSender = Playback()
    post("/quickq/pb/{playback}") {
        val arguments = call.receive<PBArguments>()
        val playback = call.parameters["playback"]?.toIntOrNull()

        if (playback == null) {
            call.respond(HttpStatusCode.BadRequest)
        } else {
            val res = playbackSender.setLevel(playback, arguments.level)
            if (res) {
                call.respond(HttpStatusCode.OK)
            } else {
                call.respond(HttpStatusCode.BadRequest)
            }
        }
    }

    post("/quickq/pb/{playback}/flash") {
        val arguments = call.receive<PBArguments>()
        val playback = call.parameters["playback"]?.toIntOrNull()

        if (playback == null) {
            call.respond(HttpStatusCode.BadRequest)
        } else {
            val res = playbackSender.flash(playback, arguments.level)
            if (res) {
                call.respond(HttpStatusCode.OK)
            } else {
                call.respond(HttpStatusCode.BadRequest)
            }
        }
    }

    post("/quickq/pb/{playback}/go") {
        val playback = call.parameters["playback"]?.toIntOrNull()

        if (playback == null) {
            call.respond(HttpStatusCode.BadRequest)
        } else {
            val res = playbackSender.go(playback)
            if (res) {
                call.respond(HttpStatusCode.OK)
            } else {
                call.respond(HttpStatusCode.BadRequest)
            }
        }
    }

    post("/quickq/pb/{playback}/pause") {
        val playback = call.parameters["playback"]?.toIntOrNull()

        if (playback == null) {
            call.respond(HttpStatusCode.BadRequest)
        } else {
            val res = playbackSender.pause(playback)
            if (res) {
                call.respond(HttpStatusCode.OK)
            } else {
                call.respond(HttpStatusCode.BadRequest)
            }
        }
    }

    post("/quickq/pb/{playback}/release") {
        val playback = call.parameters["playback"]?.toIntOrNull()

        if (playback == null) {
            call.respond(HttpStatusCode.BadRequest)
        } else {
            val res = playbackSender.release(playback)
            if (res) {
                call.respond(HttpStatusCode.OK)
            } else {
                call.respond(HttpStatusCode.BadRequest)
            }
        }
    }
}