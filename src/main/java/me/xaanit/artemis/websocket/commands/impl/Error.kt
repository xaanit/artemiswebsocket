package me.xaanit.artemis.websocket.commands.impl

import com.google.gson.JsonObject
import com.mrpowergamerbr.temmiewebhook.embed.FieldEmbed
import me.xaanit.artemis.websocket.ArtemisWebsocket
import me.xaanit.artemis.websocket.OpCode
import me.xaanit.artemis.websocket.WebhookHelper
import me.xaanit.artemis.websocket.commands.Command
import org.java_websocket.WebSocket


class Error(override val code: OpCode = OpCode.ERROR) : Command() {
    override fun handle(websocket: ArtemisWebsocket, conn: WebSocket, jsonObject: JsonObject, shard: Int) {
        val msg = jsonObject["message"].asString
        WebhookHelper.send(shard = shard, description = "Error: ${msg.substring(0, if (msg.length > (2000)) 2000 else msg.length)}", additional = {builder ->
            jsonObject.keySet().filter { it != "message" }.forEach { builder.field(FieldEmbed(it, jsonObject[it].asString, true)) }
        })
    }
}