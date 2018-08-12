package me.xaanit.artemis

import me.xaanit.artemis.commands.CommandHandler
import me.xaanit.artemis.entities.Config
import java.net.InetSocketAddress

object Application {
    @JvmStatic
    fun main(args: Array<String>) {
        val server = ArtemisWebsocket(
                CommandHandler(Config("")),
                InetSocketAddress("localhost", 8887)
        )
        val client = ArtemisClient()
        Thread {
            server.run()
        }.start()
      for(i in 0..10) {
          Thread {
              ArtemisClient().run()
          }.start()
      }

    }
}