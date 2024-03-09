package nl.avtolz.quickq.commands

import com.illposed.osc.OSCMessage
import com.illposed.osc.transport.OSCPortOut
import nl.avtolz.QUICKQ_IP
import nl.avtolz.QUICKQ_PORT
import java.net.InetAddress

class TenScene {
    private fun sendMessage(address: String, args: Any?) {
        val sender = OSCPortOut(InetAddress.getByName(QUICKQ_IP), QUICKQ_PORT)
        sender.send(OSCMessage(address, listOf(args)))
    }

    fun update(cue: Int, zone: Int, activate: Boolean): Boolean {
        if (cue in 1..10 && zone in 1..10) {
            sendMessage("/10scene/$cue/$zone", if (activate) 1 else 0)
            return true
        } else return false
    }
}