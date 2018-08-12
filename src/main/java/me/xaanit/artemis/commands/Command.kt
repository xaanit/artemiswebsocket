package me.xaanit.artemis.commands

import com.google.gson.JsonObject
import me.xaanit.artemis.ArtemisWebsocket
import me.xaanit.artemis.OpCode
import org.java_websocket.WebSocket


abstract class Command {
    abstract val code: OpCode

    abstract  fun handle(websocket: ArtemisWebsocket, conn: WebSocket, jsonObject: JsonObject, shard: Int)


}