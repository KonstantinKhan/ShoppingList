package com.shopping_list_bot.common.models

import kotlinx.serialization.Serializable

@Serializable
data class Response(
    val ok: Boolean,
    val result: ReturnedMessage
)
