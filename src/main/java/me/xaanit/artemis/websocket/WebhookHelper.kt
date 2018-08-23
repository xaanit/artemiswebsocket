package me.xaanit.artemis.websocket

import com.mrpowergamerbr.temmiewebhook.DiscordEmbed
import com.mrpowergamerbr.temmiewebhook.DiscordMessage
import com.mrpowergamerbr.temmiewebhook.TemmieWebhook
import java.time.Instant
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.util.concurrent.atomic.AtomicInteger

object WebhookHelper {

     lateinit var array: Array<String>
    private val i = AtomicInteger(0)
    fun send(shard: Int, description: String, additional: (DiscordEmbed.DiscordEmbedBuilder) -> Unit = {}) {
        if (i.get() == 5) i.set(0)
        val temmie = TemmieWebhook(array[i.getAndIncrement()])
        val dm = DiscordMessage()
        dm.username = "Datadog"
        dm.avatarUrl = "https://cdn.vox-cdn.com/thumbor/0uPjB28oVl1uDXmLgmNFxnaTiJU=/0x0:5312x3001/920x613/filters:focal(2232x1077:3080x1925):format(webp)/cdn.vox-cdn.com/uploads/chorus_image/image/58581007/shutterstock_116697148.0.jpg"
        val builder = DiscordEmbed.builder()
                .color(2398617)
                .title("Shard #$shard")
                .description(description)
                .timestamp(Instant.now().atZone(ZoneOffset.UTC).format(DateTimeFormatter.ISO_OFFSET_DATE_TIME))
        additional(builder)
        dm.embeds.add(builder.build())
        temmie.sendMessage(dm)
    }
}