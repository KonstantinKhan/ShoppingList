package ru.ktglib.transport.models.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.ktglib.transport.models.InlineKeyboardMarkup
import ru.ktglib.transport.models.message.MessageEntity

@Serializable
data class EditMessageTextModel(
    @SerialName("chat_id")
    val chatId: Int? = null,
    @SerialName("message_id")
    val messageId: Int? = null,
    @SerialName("inline_message_id")
    val inlineMessageId: String? = null,
    val text: String,
    @SerialName("parse_mode")
    val parseMode: String? = null,
    val entities: Array<MessageEntity>? = null,
    @SerialName("disable_web_page_preview")
    val disableWebPagePreview: Boolean? = null,
    @SerialName("reply_markup")
    val replyMarkup: InlineKeyboardMarkup? = null
)
