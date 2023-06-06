package com.shopping_list.be.lib.telegram.core.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MessageResponse(
    @SerialName("message_id")
    val messageId: Int,
    val from: User,
    val chat: Chat,
    val date: Int,
    val text: String
)
