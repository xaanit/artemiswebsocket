package me.xaanit.artemis.websocket.commands.impl

import com.google.gson.JsonObject
import me.xaanit.artemis.websocket.ArtemisWebsocket
import me.xaanit.artemis.websocket.OpCode
import me.xaanit.artemis.websocket.commands.Command
import org.java_websocket.WebSocket

class SendStats(override val code: OpCode = OpCode.SEND_STATS) : Command() {
    override fun handle(websocket: ArtemisWebsocket, conn: WebSocket, jsonObject: JsonObject, shard: Int) {
        val guilds = jsonObject.get("guilds").asLong
        val users = jsonObject.get("users").asLong
        val channels = jsonObject.get("channels").asLong

    }
}