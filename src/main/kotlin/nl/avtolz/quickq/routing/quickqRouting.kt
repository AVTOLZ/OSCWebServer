package nl.avtolz.quickq.routing

import io.ktor.server.routing.*

fun Routing.quickqRouting() {
    feedbackRouting()
    playbackRouting()
    tenSceneRouting()
}