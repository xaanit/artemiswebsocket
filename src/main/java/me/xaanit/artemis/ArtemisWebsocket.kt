package me.xaanit.artemis

import com.github.salomonbrys.kotson.jsonObject
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import me.xaanit.artemis.commands.CommandHandler
import org.java_websocket.WebSocket
import org.java_websocket.handshake.ClientHandshake
import org.java_websocket.server.WebSocketServer
import java.net.InetSocketAddress

class ArtemisWebsocket(
        private val handler: CommandHandler,
        address: InetSocketAddress
) : WebSocketServer(address) {

    private var connections: Map<WebSocket, Int> = mapOf()

    override fun onOpen(conn: WebSocket, handshake: ClientHandshake) {
        conn.send(jsonObject(
                "op" to 0,
                "d" to jsonObject()
        ))
    }

    override fun onClose(conn: WebSocket, code: Int, reason: String, remote: Boolean) {

    }

    override fun onMessage(conn: WebSocket, message: String) {
        println("Got message from client with conn [${connections[conn]}] ${conn.localSocketAddress.address}: $message")
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


    private fun WebSocket.send(json: JsonObject) {
        send(json.toString())
    }

    fun addConnection(conn: WebSocket, shard: Int) {
        this.connections += conn to shard
    }
}