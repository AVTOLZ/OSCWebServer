package nl.avtolz.quickq.commands

import com.illposed.osc.OSCMessage
import com.illposed.osc.transport.OSCPortOut
import nl.avtolz.QUICKQ_IP
import nl.avtolz.QUICKQ_PORT
import java.net.InetAddress

class Playback {
    private fun sendMessage(address: String, args: Any?) {
        val sender = OSCPortOut(InetAddress.getByName(QUICKQ_IP), QUICKQ_PORT)
        sender.send(OSCMessage(address, listOf(args)))
    }

    /**
     * Playback: Int in 1..10
     * Level: Int in 0..100
     */
    fun setLevel(playback: Int, level: Int): Boolean {
        if (playback in 1..10 && level in 0..100) {
            sendMessage("/pb/$playback", level)
            return true
        } else {
            return false
        }
    }

    /**
     * Playback: Int in 1..10
     * Level: Int in 0..100
     */
    fun flash(playback: Int, level: Int): Boolean {
        if (playback in 1..10) {
            sendMessage("/pb/$playback/flash", level)
            return true
        } else {
            return false
        }
    }

    /**
     * Playback: Int in 1..10
     */
    fun go(playback: Int): Boolean {
        if (playback in 1..10) {
            sendMessage("/pb/$playback/go", null)
            return true
        } else {
            return false
        }
    }

    /**
     * Playback: Int in 1..10
     */
    fun pause(playback: Int): Boolean {
        if (playback in 1..10) {
            sendMessage("/pb/$playback/pause", null)
            return true
        } else {
            return false
        }
    }

    /**
     * Playback: Int in 1..10
     */
    fun release(playback: Int): Boolean {
        if (playback in 1..10) {
            sendMessage("/pb/$playback/release", null)
            return true
        } else {
            return false
        }
    }
}