package com.shopping_list.lib.telegram.api.dsl

import com.shopping_list.lib.telegram.api.dsl.interfaces.IDslBuildable
import ru.ktglib.transport.models.ReplyMarkup
import ru.ktglib.transport.models.request.SendMessageModel

fun message(block: SendMessageBuilder.() -> Unit) = SendMessageBuilder().apply(block).build()

class SendMessageBuilder(
    var chatId: Int = 0,
    var text: String = "",
    var replyMarkup: ReplyMarkup? = null,
    var parseMode: String? = null
) : IDslBuildable<SendMessageModel> {
    override fun build() = SendMessageModel(
        chatId = chatId,
        text = text,
        replyMarkup = replyMarkup,
        parseMode = parseMode
    )
}