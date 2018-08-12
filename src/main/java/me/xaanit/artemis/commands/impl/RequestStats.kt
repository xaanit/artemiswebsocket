package me.xaanit.artemis.commands.impl

import com.google.gson.JsonObject
import me.xaanit.artemis.ArtemisWebsocket
import me.xaanit.artemis.OpCode
import me.xaanit.artemis.commands.Command
import org.java_websocket.WebSocket

class RequestStats(override val code: OpCode = OpCode.REQUEST_STATS) : Command() {
    override fun handle(websocket: ArtemisWebsocket, conn: WebSocket, jsonObject: JsonObject, shard: Int) {

    }
}