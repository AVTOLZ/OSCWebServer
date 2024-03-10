package nl.avtolz.x32.routing

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import nl.avtolz.BoolArg
import nl.avtolz.DoubleArg
import nl.avtolz.IntArg
import nl.avtolz.StringArg
import nl.avtolz.x32.commands.Config

fun Routing.configRouting() {
    val configSender = Config()
    post("/x32/config/chlink/{channel}") {
        val arguments = call.receive<BoolArg>()
        val channel = call.parameters["channel"]?.toIntOrNull()

        if (channel == null) {
            call.respond(HttpStatusCode.BadRequest)
        } else {
            val res = configSender.link("chlink", channel, arguments.arg)
            if (res) {
                call.respond(HttpStatusCode.OK)
            } else {
                call.respond(HttpStatusCode.BadRequest)
            }
        }
    }
    
    post("/x32/config/auxlink/{channel}") {
        val arguments = call.receive<BoolArg>()
        val channel = call.parameters["channel"]?.toIntOrNull()

        if (channel == null) {
            call.respond(HttpStatusCode.BadRequest)
        } else {
            val res = configSender.link("auxlink", channel, arguments.arg)
            if (res) {
                call.respond(HttpStatusCode.OK)
            } else {
                call.respond(HttpStatusCode.BadRequest)
            }
        }
    }
    
    post("/x32/config/fxlink/{channel}") {
        val arguments = call.receive<BoolArg>()
        val channel = call.parameters["channel"]?.toIntOrNull()

        if (channel == null) {
            call.respond(HttpStatusCode.BadRequest)
        } else {
            val res = configSender.link("fxlink", channel, arguments.arg)
            if (res) {
                call.respond(HttpStatusCode.OK)
            } else {
                call.respond(HttpStatusCode.BadRequest)
            }
        }
    }
    
    post("/x32/config/buslink/{channel}") {
        val arguments = call.receive<BoolArg>()
        val channel = call.parameters["channel"]?.toIntOrNull()

        if (channel == null) {
            call.respond(HttpStatusCode.BadRequest)
        } else {
            val res = configSender.link("buslink", channel, arguments.arg)
            if (res) {
                call.respond(HttpStatusCode.OK)
            } else {
                call.respond(HttpStatusCode.BadRequest)
            }
        }
    }
    
    post("/x32/config/mtxlink/{channel}") {
        val arguments = call.receive<BoolArg>()
        val channel = call.parameters["channel"]?.toIntOrNull()

        if (channel == null) {
            call.respond(HttpStatusCode.BadRequest)
        } else {
            val res = configSender.link("mtxlink", channel, arguments.arg)
            if (res) {
                call.respond(HttpStatusCode.OK)
            } else {
                call.respond(HttpStatusCode.BadRequest)
            }
        }
    }
    
    
    post("/x32/config/mute/{group}") {
        val arguments = call.receive<BoolArg>()
        val group = call.parameters["group"]?.toIntOrNull()

        if (group == null) {
            call.respond(HttpStatusCode.BadRequest)
        } else {
            val res = configSender.muteGroup(group, arguments.arg)
            if (res) {
                call.respond(HttpStatusCode.OK)
            } else {
                call.respond(HttpStatusCode.BadRequest)
            }
        }
    }

    post("/x32/config/linkcfg/hadly") {
        val arguments = call.receive<BoolArg>()

        configSender.linkConfigHADelay(arguments.arg)
        call.respond(HttpStatusCode.OK)
    }

    post("/x32/config/linkcfg/eq") {
        val arguments = call.receive<BoolArg>()

        configSender.linkConfigEQ(arguments.arg)
        call.respond(HttpStatusCode.OK)
    }

    post("/x32/config/linkcfg/dyn") {
        val arguments = call.receive<BoolArg>()

        configSender.linkConfigDynamic(arguments.arg)
        call.respond(HttpStatusCode.OK)
    }

    post("/x32/config/linkcfg/fdrmute") {
        val arguments = call.receive<BoolArg>()

        configSender.linkConfigFaderMute(arguments.arg)
        call.respond(HttpStatusCode.OK)
    }

    post("/x32/config/mono/mode") {
        val arguments = call.receive<IntArg>()

        configSender.monoMode(arguments.arg)
        call.respond(HttpStatusCode.OK)
    }

    post("/x32/config/mono/link") {
        val arguments = call.receive<BoolArg>()

        configSender.monoLink(arguments.arg)
        call.respond(HttpStatusCode.OK)
    }

    post("/x32/config/solo/level") {
        val arguments = call.receive<DoubleArg>()

        configSender.soloLevel(arguments.arg)
        call.respond(HttpStatusCode.OK)
    }

    post("/x32/config/solo/source") {
        val arguments = call.receive<StringArg>()

        configSender.soloSource(arguments.arg)
        call.respond(HttpStatusCode.OK)
    }

    post("/x32/config/solo/source") {
        val arguments = call.receive<DoubleArg>()

        configSender.soloSourceTrim(arguments.arg)
        call.respond(HttpStatusCode.OK)
    }
}