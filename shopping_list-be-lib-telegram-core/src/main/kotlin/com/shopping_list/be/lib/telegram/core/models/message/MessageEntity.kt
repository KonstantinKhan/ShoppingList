package com.shopping_list.be.lib.telegram.core.models.message

import com.shopping_list.be.lib.telegram.core.models.User
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MessageEntity(
    val type: String,
    val offset: Int,
    val length: Int,
    val url: String? = null,
    val user: User? = null,
    val language: String? = null,
    @SerialName("custom_emoji_id")
    val customEmojiId: String? = null
)
