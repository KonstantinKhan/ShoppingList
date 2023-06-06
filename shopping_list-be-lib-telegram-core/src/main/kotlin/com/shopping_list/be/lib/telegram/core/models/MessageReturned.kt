package com.shopping_list.be.lib.telegram.core.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Response(
    val ok: Boolean,
    val result: MessageReturned
)

@Serializable
@SerialName("result")
data class MessageReturned(
    @SerialName("message_id")
    val messageId: Int
)
