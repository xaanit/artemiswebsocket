package me.xaanit.artemis.websocket

import com.google.gson.Gson
import me.xaanit.artemis.websocket.commands.CommandHandler
import me.xaanit.artemis.websocket.entities.Config
import java.io.File
import java.net.InetSocketAddress
import java.util.concurrent.atomic.AtomicInteger

object Application {
    val i = AtomicInteger(0)

    @JvmStatic
    fun main(args: Array<String>) {
        val config = readConfig()
        WebhookHelper.array = config.webhookUrls.toTypedArray()
        ArtemisWebsocket(
                CommandHandler(config),
                InetSocketAddress("localhost", 8887)
        ).run()

    }

    fun readConfig(): Config {
        val file = File("${System.getProperty("user.dir")}/config.json")
        val json = file.readText()
        return Gson().fromJson(json, Config::class.java)
    }
}