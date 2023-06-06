package com.shopping_list.common.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("result")
data class ReturnedMessage(
    @Serializable(with = MessageIdSerializer::class)
    @SerialName("message_id")
    val messageId: MessageId
)
