package me.xaanit.artemis.websocket.commands

import com.google.gson.JsonObject
import me.xaanit.artemis.websocket.ArtemisWebsocket
import me.xaanit.artemis.websocket.OpCode
import me.xaanit.artemis.websocket.entities.Config
import org.java_websocket.WebSocket
import org.reflections.Reflections

class CommandHandler(val config: Config) {
    private val commands: Map<OpCode, Command>

    init {
        var map: Map<OpCode, Command> = mapOf()
        Reflections("me.xaanit.artemis.websocket.commands.impl").getSubTypesOf(Command::class.java).forEach {
            val cmd = it.newInstance()
            map += cmd.code to cmd
        }
        commands = map
    }

    fun handle(websocket: ArtemisWebsocket, conn: WebSocket, jsonObject: JsonObject, shard: Int) {
        val code = OpCode.valueOf(jsonObject.get("op").asInt)
        println("OP CODE: $code")
        println("COMMANDS: $commands")
        commands[code]?.handle(websocket, conn, jsonObject.getAsJsonObject("d"), shard) ?: error("Unknown op code.")
    }


}