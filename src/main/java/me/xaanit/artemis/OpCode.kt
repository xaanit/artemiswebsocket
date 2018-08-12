package me.xaanit.artemis

enum class OpCode(
        val code: Int
) {
    REQUEST_IDENTIFY(0),
    IDENTIFY(1),
    ERROR(2),
    SEND_STATS(3),
    REQUEST_STATS(4);


    companion object {
        fun valueOf(op: Int): OpCode {
            return values().find { it.code == op }!!
        }
    }
}