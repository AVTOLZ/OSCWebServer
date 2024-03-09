package nl.avtolz.quickq.routing

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.Serializable
import nl.avtolz.quickq.commands.TenScene

@Serializable
data class TSArguments(val activate: Boolean)

fun Routing.tenSceneRouting() {
    val tenSceneSender = TenScene()

    post("/quickq/10scene/{cue}/{zone}") {
        val arguments = call.receive<TSArguments>()
        val cue = call.parameters["cue"]?.toIntOrNull()
        val zone = call.parameters["zone"]?.toIntOrNull()

        if (cue == null || zone == null) {
            call.respond(HttpStatusCode.BadRequest)
        } else {
            val res = tenSceneSender.update(cue, zone, arguments.activate)
            if (res) {
                call.respond(HttpStatusCode.OK)
            } else {
                call.respond(HttpStatusCode.BadRequest)
            }
        }
    }
}