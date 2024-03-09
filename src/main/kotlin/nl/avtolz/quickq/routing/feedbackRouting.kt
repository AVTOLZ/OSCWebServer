package nl.avtolz.quickq.routing

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import nl.avtolz.quickq.commands.Feedback

fun Routing.feedbackRouting() {
    val feedbackSender = Feedback()

    post("/quickq/feedback/off") {
        feedbackSender.off()
        call.respond(HttpStatusCode.OK)
    }

    post("/quickq/feedback/pb") {
        feedbackSender.pb()
        call.respond(HttpStatusCode.OK)
    }

    post("/quickq/feedback/pb+exec") {
        feedbackSender.all()
        call.respond(HttpStatusCode.OK)
    }

    post("/quickq/feedback/exec") {
        feedbackSender.exec()
        call.respond(HttpStatusCode.OK)
    }
}