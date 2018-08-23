package me.xaanit.artemis.websocket.commands.impl

import com.google.gson.JsonObject
import me.xaanit.artemis.websocket.ArtemisWebsocket
import me.xaanit.artemis.websocket.OpCode
import me.xaanit.artemis.websocket.WebhookHelper
import me.xaanit.artemis.websocket.commands.Command
import org.java_websocket.WebSocket


class Error(override val code: OpCode = OpCode.ERROR) : Command() {
    override fun handle(websocket: ArtemisWebsocket, conn: WebSocket, jsonObject: JsonObject, shard: Int) {
        val msg = jsonObject.get("message").asString
        WebhookHelper.send(shard, "Error: ${msg.substring(0, if (msg.length > (2000)) 2000 else msg.length)}")
    }
}