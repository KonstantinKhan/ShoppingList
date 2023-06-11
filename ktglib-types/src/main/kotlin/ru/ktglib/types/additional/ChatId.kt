package ru.ktglib.types.additional

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ChatId(
    @SerialName("chat_id")
    private val id: String
) {
    constructor(id: Long) : this(id.toString())
}
