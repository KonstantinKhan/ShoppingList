package ru.ktglib.transport.models.message

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.ktglib.transport.models.TgUser

@Serializable
data class MessageEntity(
    val type: String,
    val offset: Int,
    val length: Int,
    val url: String? = null,
    val user: TgUser? = null,
    val language: String? = null,
    @SerialName("custom_emoji_id")
    val customEmojiId: String? = null
)
