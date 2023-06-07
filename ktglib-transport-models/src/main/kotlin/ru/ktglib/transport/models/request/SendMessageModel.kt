package ru.ktglib.transport.models.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.ktglib.transport.models.ReplyMarkup

@Serializable
data class SendMessageModel(
    @SerialName("chat_id")
    val chatId: Long,
    val text: String,
    @SerialName("reply_markup")
    val replyMarkup: ReplyMarkup? = null,
    @SerialName("parse_mode")
    val parseMode: String? = null
)
