package nl.avtolz.quickq.commands

import com.illposed.osc.OSCMessage
import com.illposed.osc.transport.OSCPortOut
import nl.avtolz.QUICKQ_IP
import nl.avtolz.QUICKQ_PORT
import java.net.InetAddress

class Feedback {
    private fun sendMessage(address: String) {
        val sender = OSCPortOut(InetAddress.getByName(QUICKQ_IP), QUICKQ_PORT)
        sender.send(OSCMessage(address, listOf(null)))
    }

    fun off() {
        sendMessage("/feedback/off")
    }

    fun pb() {
        sendMessage("/feedback/pb")
    }

    fun exec() {
        sendMessage("/feedback/exec")
    }

    fun all() {
        sendMessage("/feedback/pb+exec")
    }
}