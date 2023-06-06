package com.shopping_list.be.lib.telegram.core.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MessageRequest(
    @SerialName("chat_id")
    val chatId: Int,
    @SerialName("message_id")
    val messageId: Int? = null,
    val text: String,
    @SerialName("reply_markup")
    val replyMarkup: ReplyMarkup? = null,
    @SerialName("parse_mode")
    val parseMode: String? = null
)