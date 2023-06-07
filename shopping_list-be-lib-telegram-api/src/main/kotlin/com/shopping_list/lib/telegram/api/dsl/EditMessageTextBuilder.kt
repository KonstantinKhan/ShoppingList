package com.shopping_list.lib.telegram.api.dsl

import com.shopping_list.lib.telegram.api.dsl.interfaces.IDslBuildable
import ru.ktglib.transport.models.InlineKeyboardMarkup
import ru.ktglib.transport.models.request.EditMessageTextModel
fun editMessageText(block: EditMessageTextBuilder.() -> Unit) = EditMessageTextBuilder().apply(block).build()
data class EditMessageTextBuilder(
    var chatId: Long? = null,
    var messageId: Int? = null,
    var text: String = "",
    var parseMode: String? = null,
    var replyMarkup: InlineKeyboardMarkup? = null
) : IDslBuildable<EditMessageTextModel> {
    override fun build() = EditMessageTextModel(
        chatId = chatId,
        messageId = messageId,
        text = text,
        parseMode = parseMode,
        replyMarkup = replyMarkup
    )
}
