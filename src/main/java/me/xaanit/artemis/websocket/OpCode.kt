package me.xaanit.artemis.websocket

enum class OpCode(
        val code: Int,
        val sentToServer: Boolean = false,
        val sentToClient: Boolean = false
) {
    REQUEST_IDENTIFY(0, true),
    IDENTIFY(1, true),
    ERROR(2, true, true),
    SEND_STATS(3, true),
    REQUEST_STATS(4, true),
    RESTART(5, true);


    companion object {
        fun valueOf(op: Int): OpCode {
            return values().find { it.code == op }!!
        }
    }
}