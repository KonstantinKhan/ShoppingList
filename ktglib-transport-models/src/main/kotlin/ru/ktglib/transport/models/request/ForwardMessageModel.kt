package ru.ktglib.transport.models.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ForwardMessageModel(
    @SerialName("chat_id")
    val chatId: Int,
    @SerialName("from_chat_id")
    val fromChatId: Int,
    @SerialName("message_id")
    val messageId: Int
)
