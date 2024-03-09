package nl.avtolz.x32.commands

import com.illposed.osc.OSCMessage
import com.illposed.osc.transport.OSCPortOut
import nl.avtolz.X32_IP
import nl.avtolz.X32_PORT
import java.net.InetAddress

class Config {
    private fun sendMessage(address: String, argument: Any?) {
        val sender = OSCPortOut(InetAddress.getByName(X32_IP), X32_PORT)
        sender.send(OSCMessage(address, listOf(argument)))
    }

    fun link(type: String, target: Int, link: Boolean): Boolean {
        val targets = if (target % 2 == 0) {
            "${target - 1}-$target"
        } else {
            "$target-${target + 1}"
        }

        if (type == "chlink" && (target < 0 || target > 32)) {
            return false
        } else if (type == "auxlink" && (target < 0 || target > 8)) {
            return false
        } else if (type == "fxlink" && (target < 0 || target > 8)) {
            return false
        } else if (type == "buslink" && (target < 0 || target > 16)) {
            return false
        } else if (type == "mtxlink" && (target < 0 || target > 6)) {
            return false
        }

        sendMessage("/config/$type/$targets", if (link) 1 else 0)
        return true
    }

    fun muteGroup(group: Int, mute: Boolean): Boolean {
        if (group < 0 || group > 6) {
            return false
        }

        sendMessage("/config/mute/$group", if (mute) 1 else 0)
        return true
    }
}