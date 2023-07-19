package ru.ktglib.transport.models.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetChatRequestModel(
    @SerialName("chat_id")
    val chatId: Long
)
