package me.xaanit.artemis.commands.impl

import com.google.gson.JsonObject
import me.xaanit.artemis.ArtemisWebsocket
import me.xaanit.artemis.OpCode
import me.xaanit.artemis.commands.Command
import org.java_websocket.WebSocket

class Identify(override val code: OpCode = OpCode.IDENTIFY) : Command() {
    override fun handle(websocket: ArtemisWebsocket, conn: WebSocket, jsonObject: JsonObject, shard: Int) {
        val realShard = jsonObject.getAsJsonObject("d").get("shard").asInt
        println("Adding connection for shard $realShard")
        websocket.addConnection(conn, realShard)
    }
}