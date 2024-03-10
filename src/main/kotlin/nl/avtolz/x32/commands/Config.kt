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

    /**
     * type: chlink, auxlink, fxlink, buslink, mtxlink
     * target: Range depends on type
     */
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

    /**
     * group: Int in 1..6
     */
    fun muteGroup(group: Int, mute: Boolean): Boolean {
        if (group < 0 || group > 6) {
            return false
        }

        sendMessage("/config/mute/$group", mute)
        return true
    }

    fun linkConfigHADelay(bool: Boolean) {
        sendMessage("/config/linkcfg/hadly", bool)
    }

    fun linkConfigEQ(bool: Boolean) {
        sendMessage("/config/linkcfg/eq", bool)
    }

    fun linkConfigDynamic(bool: Boolean) {
        sendMessage("/config/linkcfg/dyn", bool)
    }

    fun linkConfigFaderMute(bool: Boolean) {
        sendMessage("/config/linkcfg/fdrmute", bool)
    }

    /**
     * Int in 0..1
     */
    fun monoMode(value: Int): Boolean {
        if (value != 0 && value != 1) {
            return false
        }

        sendMessage("/config/mono/mode", value)
        return true
    }

    fun monoLink(bool: Boolean) {
        sendMessage("/config/mono/link", bool)
    }

    /**
     * level: Double in -90.0 .. 10.0
     */
    fun soloLevel(level: Double): Boolean {
        if (level < -90.0 || level > 10.0) {
            return false
        }

        sendMessage("/config/mono/mode", level)
        return true
    }

    /**
     * Acceptable destinations are: OFF, LR, LR+C, LRPFL, LRAFL, AUX56, AUX78
     */
    fun soloSource(destination: String): Boolean {
        val res = when(destination) {
            "OFF"   -> 0
            "LR"    -> 1
            "LR+C"  -> 2
            "LRPFL" -> 3
            "LRAFL" -> 4
            "AUX56" -> 5
            "AUX78" -> 6
            else -> {-1}
        }
        if (res == -1) {
            return false
        }

        sendMessage("/config/solo/source", res)
        return true
    }

    /**
     * What does this mean? In docs, it says this: [‐18.000, 18.000, 0.500] but what does that mean haha???
     */
    fun soloSourceTrim(level: Double) {
        sendMessage("/config/solo/sourcetrim", level)
    }

    /**
     * value: Int in 0..1
     */
    fun soloChannelMode(value: Int): Boolean {
        if (value != 0 && value != 1) {
            return false
        }

        sendMessage("/config/solo/chmode", value)
        return true
    }

    /**
     * value: Int in 0..1
     */
    fun soloBusMode(value: Int): Boolean {
        if (value != 0 && value != 1) {
            return false
        }

        sendMessage("/config/solo/busmode", value)
        return true
    }
    /**
     * value: Int in 0..1
     */
    fun soloDCAMode(value: Int): Boolean {
        if (value != 0 && value != 1) {
            return false
        }

        sendMessage("/config/solo/dcamode", value)
        return true
    }

    /**
     * value: Int in 0..1
     */
    fun soloExclusive(value: Int): Boolean {
        if (value != 0 && value != 1) {
            return false
        }

        sendMessage("/config/solo/exclusive", value)
        return true
    }

    /**
     * value: Int in 0..1
     */
    fun soloFollowSelection(value: Int): Boolean {
        if (value != 0 && value != 1) {
            return false
        }

        sendMessage("/config/solo/followsel", value)
        return true
    }

    /**
     * value: Int in 0..1
     */
    fun soloFollowSolo(value: Int): Boolean {
        if (value != 0 && value != 1) {
            return false
        }

        sendMessage("/config/solo/followsolo", value)
        return true
    }

    /**
     * What does this mean? In docs, it says this: [‐40.000, 0.000, 1.000] but what does that mean haha???
     */
    fun soloDimAttribute(level: Double) {
        sendMessage("/config/solo/dimatt", level)
    }

    /**
     * value: Int in 0..1
     */
    fun soloDim(value: Int): Boolean {
        if (value != 0 && value != 1) {
            return false
        }

        sendMessage("/config/solo/dim", value)
        return true
    }

    /**
     * value: Int in 0..1
     */
    fun soloMono(value: Int): Boolean {
        if (value != 0 && value != 1) {
            return false
        }

        sendMessage("/config/solo/mono", value)
        return true
    }

    /**
     * value: Int in 0..1
     */
    fun soloDelay(value: Int): Boolean {
        if (value != 0 && value != 1) {
            return false
        }

        sendMessage("/config/solo/delay", value)
        return true
    }

    /**
     * What does this mean? In docs, it says this: [0.300, 500.000, 0.100] but what does that mean haha???
     */
    fun soloDelayTime(level: Double) {
        sendMessage("/config/solo/delaytime", level)
    }

    /**
     * value: Int in 0..1
     */
    fun soloMasterControl(value: Int): Boolean {
        if (value != 0 && value != 1) {
            return false
        }

        sendMessage("/config/solo/masterctrl", value)
        return true
    }

    /**
     * value: Int in 0..1
     */
    fun soloMute(value: Int): Boolean {
        if (value != 0 && value != 1) {
            return false
        }

        sendMessage("/config/solo/mute", value)
        return true
    }

    /**
     * value: Int in 0..1
     */
    fun soloDimPFL(value: Int): Boolean {
        if (value != 0 && value != 1) {
            return false
        }

        sendMessage("/config/solo/dimpfl", value)
        return true
    }

    /**
     * value: Int in 0..1
     */
    fun talkEnable(value: Int): Boolean {
        if (value != 0 && value != 1) {
            return false
        }

        sendMessage("/config/talk/enable", value)
        return true
    }

    /**
     * value: Int in 0..1
     */
    fun talkSource(value: Int): Boolean {
        if (value != 0 && value != 1) {
            return false
        }

        sendMessage("/config/talk/source", value)
        return true
    }

    /**
     * level: Double in -90.0 .. 10.0
     */
    fun talkALevel(level: Double) {
        sendMessage("/config/talk/A/level", level)
    }

    /**
     * level: Double in -90.0 .. 10.0
     */
    fun talkBLevel(level: Double) {
        sendMessage("/config/talk/A/level", level)
    }

    /**
     * value: Int in 0..1
     */
    fun talkALatch(value: Int): Boolean {
        if (value != 0 && value != 1) {
            return false
        }

        sendMessage("/config/talk/A/latch", value)
        return true
    }

    /**
     * value: Int in 0..1
     */
    fun talkBLatch(value: Int): Boolean {
        if (value != 0 && value != 1) {
            return false
        }

        sendMessage("/config/talk/B/latch", value)
        return true
    }

    /**
     * value: Int in 0..1
     */
    fun talkADim(value: Int): Boolean {
        if (value != 0 && value != 1) {
            return false
        }

        sendMessage("/config/talk/A/dim", value)
        return true
    }

    /**
     * value: Int in 0..1
     */
    fun talkBDim(value: Int): Boolean {
        if (value != 0 && value != 1) {
            return false
        }

        sendMessage("/config/talk/B/dim", value)
        return true
    }

    /**
     * value: Int in 0..262143
     */
    fun talkADestMap(value: Int): Boolean {
        if (value < 0 || value > 262143) {
            return false
        }

        sendMessage("/config/talk/A/destmap", value)
        return true
    }

    /**
     * value: Int in 0..262143
     */
    fun talkBDestMap(value: Int): Boolean {
        if (value < 0 || value > 262143) {
            return false
        }

        sendMessage("/config/talk/B/destmap", value)
        return true
    }

    /**
     * level: Double in -90.0 .. 10.0
     */
    fun oscillatorLevel(level: Double) {
        sendMessage("/config/osc/level", level)
    }

    /**
     * freq: Double in 20.0 .. 20000.0
     * target: Int in 1..2
     */
    fun oscillatorFrequency(freq: Double, target: Int): Boolean {
        if (freq < 20.0 || freq > 20000.0 || target < 1 || target > 2) {
            return false
        }

        sendMessage("/config/osc/f$target", freq)
        return true
    }

    /**
     * value: Int in 1..2
     */
    fun oscillatorFrequencySelect(value: Int): Boolean {
        if (value != 1 && value != 2) {
            return false
        }

        sendMessage("/config/talk/B/dim", value)
        return true
    }

    /**
     * value: Int in 0..2 representing Pure Sine, Pink Noise, White Noise
     */
    fun oscillatorType(value: Int): Boolean {
        if (value != 1 && value != 2) {
            return false
        }

        sendMessage("/config/talk/B/dim", value)
        return true
    }

    /**
     * target: Int in 0..25 representing MixBus1..16, L, R, L+R, M/C, Matrix1..6
     */
    fun oscillatorDestination(target: Int): Boolean {
        if (target < 0 || target > 25) {
            return false
        }

        sendMessage("/config/osc/dest", target)
        return true
    }

    /**
     * source: Int in 1..48
     * target: Int in 0..208 representing:
     * 0 OFF,
     * 1…32 Local In 1…32,
     * 33…80 AES50-A 1…48,
     * 81…128 AES50-B 1…48,
     * 129…160 Card In 1…32,
     * 161…166 Aux In 1…6,
     * 167 TB Internal,
     * 168 TB External,
     * 169…184 Outputs 1…16,
     * 185…200 P16 1…16,
     * 201…206 AUX 1…6,
     * 207 Monitor L,
     * 208 Monitor R,
     */
    fun userRouteOut(source: Int, target: Int): Boolean {
        if (target < 0 || target > 208 || source < 1 || source > 48) {
            return false
        }

        sendMessage("/config/userrout/out/$source", target)
        return true
    }

    /**
     * source: Int in 1..32
     * target: Int in 0..168 representing:
     * 0 OFF
     * 1…32 Local In 1…32
     * 33…80 AES50-A 1…48
     * 81…128 AES50-B 1…48
     * 129…160 Card In 1…32
     * 161…166 Aux In 1…6
     * 167 TB Internal
     * 168 TB External
     */
    fun userRouteIn(source: Int, target: Int): Boolean {
        if (target < 0 || target > 168 || source < 1 || source > 32) {
            return false
        }

        sendMessage("/config/userrout/in/$source", target)
        return true
    }

    /**
     * value: 0=Red, 1=Playback
     */
    fun routingRouteSwitch(value: Int): Boolean {
        if (value < 0 || value > 1) {
            return false
        }

        sendMessage("/config/routing/routswitch", value)
        return true
    }

    /**
     * source: Int in 0..3 representing:
     * 0 1-8
     * 1 9-16
     * 2 17-24
     * 3 25-32
     *
     * target: Int in 0..23 representing:
     * AN1-8, AN9-16, AN17-24, AN25-32,
     * A1-8, A9-16, A17-24, A25-32, A33-40, A41-
     * 48, B1-8, B9-16, B17-24, B25-32, B33-40,
     * B41-48, CARD1-8, CARD9-16, CARD17-24,
     * CARD25-32, UIN1-8, UIN9-16, UIN17-24,
     * UIN25-32
     */
    fun routingIn(source: Int, target: Int): Boolean {
        if (target < 0 || target > 23 || source < 0 || source > 3) {
            return false
        }

        val res = when(source) {
            0       -> "1-8"
            1       -> "9-16"
            2       -> "17-24"
            3       -> "25-32"
            else    -> ""
        }

        sendMessage("/config/routing/in/$res", target)
        return true
    }

    /**
     * target: Int in 0..15 representing:
     * AUX1-4, AN1-2, AN1-4, AN1-6, A1-2, A1-4
     * A1-6, B1-2, B1-4, B1-6, CARD1-2, CARD1-4,
     * CARD1-6, UIN1-2, UIN1-4, UIN1-6
     */
    fun routingInAux(target: Int): Boolean {
        if (target < 0 || target > 15) {
            return false
        }

        sendMessage("/config/routing/in/AUX", target)
        return true
    }

    /**
     * source: String one of these:
     * AES50A/1-8
     * AES50A/9-16
     * AES50A/17-24
     * AES50A/25-32
     * AES50A/33-40
     * AES50A/41-48
     * AES50B/1-8
     * AES50B/9-16
     * AES50B/17-24
     * AES50B/25-32
     * AES50B/33-40
     * AES50B/41-48
     * CARD/1-8
     * CARD/9-16
     * CARD/17-24
     * CARD/25-32
     * 
     * target: Int in 0..35 representing:
     * AN1-8, AN9-16, AN17-24, AN25-32,
     * A1-8, A9-16, A17-24, A25-32, A33-40, A41-8,
     * B1-8, B9-16, B17-24, B25-32, B33-40,
     * B41-48, CARD1-8, CARD9-16, CARD17-24,
     * CARD25-32, OUT1-8, OUT9-16, P161-8, P16
     * 9-16, AUX1-6/Mon, AuxIN1-6/TB, UOUT1-8,
     * UOUT9-16, UOUT17-24, UOUT25-32, UOUT33-40,
     * UOUT41-48, UIN1-8, UIN9-16, UIN17-24,
     * UIN25-32
     */
    fun routing(source: String, target: Int): Boolean {
        if (target < 0 || target > 35) {
            return false
        }

        sendMessage("/config/routing/$source", target)
        return true
    }

    // TODO Finish the rest of the routing. I am stopping here because the rest of the methods seem unimportant
}