package ru.ktglib.types

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ChatShared(
    @SerialName("request_id")
    val requestId: Int,
    @SerialName("chat_id")
    val chatId: Int
)
