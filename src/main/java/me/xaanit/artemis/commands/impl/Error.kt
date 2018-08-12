package me.xaanit.artemis.commands.impl

import com.google.gson.JsonObject
import me.xaanit.artemis.ArtemisWebsocket
import me.xaanit.artemis.OpCode
import me.xaanit.artemis.commands.Command
import org.java_websocket.WebSocket

class Error(override val code: OpCode = OpCode.ERROR) : Command() {
    override fun handle(websocket: ArtemisWebsocket, conn: WebSocket, jsonObject: JsonObject, shard: Int) {
        System.err.println("Got error from shard $shard: ${jsonObject.get("message").asString}")
    }
}