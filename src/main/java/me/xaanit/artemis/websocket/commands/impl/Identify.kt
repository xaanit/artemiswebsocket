package me.xaanit.artemis.websocket.commands.impl

import com.google.gson.JsonObject
import me.xaanit.artemis.websocket.ArtemisWebsocket
import me.xaanit.artemis.websocket.OpCode
import me.xaanit.artemis.websocket.WebhookHelper
import me.xaanit.artemis.websocket.commands.Command
import org.java_websocket.WebSocket

class Identify(override val code: OpCode = OpCode.IDENTIFY) : Command() {
    override fun handle(websocket: ArtemisWebsocket, conn: WebSocket, jsonObject: JsonObject, shard: Int) {
        println("Adding connection for shard $shard")
        WebhookHelper.send(shard = shard, description = "Connected.")
                // websocket.send(json)
    }
}