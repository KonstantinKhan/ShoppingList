package com.shopping_list.be.lib.telegram.core.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DeleteMessageRequest(
    @SerialName("chat_id")
    val chatId: Int,
    @SerialName("message_id")
    val messageId: Int
)
