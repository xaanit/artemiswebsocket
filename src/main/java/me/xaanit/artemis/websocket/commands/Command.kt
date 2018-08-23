package me.xaanit.artemis.websocket.commands

import com.google.gson.JsonObject
import me.xaanit.artemis.websocket.ArtemisWebsocket
import me.xaanit.artemis.websocket.OpCode
import org.java_websocket.WebSocket


abstract class Command {
    abstract val code: OpCode

    abstract  fun handle(websocket: ArtemisWebsocket, conn: WebSocket, jsonObject: JsonObject, shard: Int)

    override fun toString(): String {
        return "Command[code=$code]"
    }
}