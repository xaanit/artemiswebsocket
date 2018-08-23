package me.xaanit.artemis.websocket

import com.github.salomonbrys.kotson.jsonObject
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import me.xaanit.artemis.websocket.commands.CommandHandler
import org.java_websocket.WebSocket
import org.java_websocket.handshake.ClientHandshake
import org.java_websocket.server.WebSocketServer
import java.net.InetSocketAddress

class ArtemisWebsocket(
        private val handler: CommandHandler,
        address: InetSocketAddress
) : WebSocketServer(address) {

    override fun onOpen(conn: WebSocket, handshake: ClientHandshake) {
        conn.send(jsonObject(
                "op" to OpCode.REQUEST_IDENTIFY.code,
                "d" to jsonObject()
        ))
    }

    override fun onClose(conn: WebSocket, code: Int, reason: String, remote: Boolean) {

    }

    override fun onMessage(conn: WebSocket, message: String) {
        println("Got message from client with conn ${conn.localSocketAddress.address}: $message")
        val obj = JsonParser().parse(message).asJsonObject
        handler.handle(this, conn, obj, obj.get("shard")?.asInt ?: 0)
    }

    override fun onStart() {

    }

    override fun onError(conn: WebSocket, err: Exception) {
        conn.send(jsonObject(
                "op" to 1,
                "d" to jsonObject(
                        "message" to err.message
                )
        ))
    }


    fun WebSocket.send(json: JsonObject) {
        send(json.toString())
    }

    fun send(json: JsonObject) {
        connections.forEach { it.send(json) }
    }
}