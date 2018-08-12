package me.xaanit.artemis

import com.github.salomonbrys.kotson.jsonObject
import com.google.gson.JsonObject
import org.java_websocket.client.WebSocketClient
import org.java_websocket.handshake.ServerHandshake
import java.net.URI
import java.util.concurrent.atomic.AtomicInteger

class ArtemisClient : WebSocketClient(URI("ws://localhost:8887")) {
    companion object {
        val i = AtomicInteger(0)
    }
    override fun onOpen(handshake: ServerHandshake?) {
        send(jsonObject(
                "op" to 1,
                "d" to jsonObject(
                        "shard" to i.getAndIncrement(),
                        "guilds" to 300,
                        "users" to 5000
                )
        ))

        Thread {
            Thread.sleep(4000)
            send(jsonObject(
                    "op" to 2,
                    "d" to jsonObject(
                            "message" to "hello, world"
                    )
            ))
        }.start()
    }

    override fun onClose(p0: Int, p1: String?, p2: Boolean) {

    }

    override fun onMessage(message: String?) {
        println("Got message from server: $message")
    }

    override fun onError(p0: Exception?) {
    }

    private fun WebSocketClient.send(json: JsonObject) {
        send(json.toString())
    }

}