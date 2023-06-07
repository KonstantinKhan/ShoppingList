package ru.ktglib.types

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.decodeFromJsonElement
import kotlinx.serialization.json.encodeToJsonElement
import ru.ktglib.types.Chat.Companion.CHAT_FULL_JSON
import ru.ktglib.types.Chat.Companion.CHAT_PART_JSON
import ru.ktglib.types.Chat.Companion.CHAT_REDUNDANT_PROPERTIES_JSON
import ru.ktglib.types.Chat.Companion.FULL_CHAT
import ru.ktglib.types.Chat.Companion.PART_CHAT
import ru.ktglib.types.Json.json
import ru.ktglib.types.User.Companion.FULL_USER
import ru.ktglib.types.User.Companion.USER_FULL_JSON


@Serializable
data class Message(
    @SerialName("message_id")
    val messageId: Int,
    @SerialName("from")
    val user: User? = null,
    val date: Int = 0,
    val chat: Chat,
    val text: String? = null,
    @SerialName("user_shared")
    val userShared: UserShared? = null,
    @SerialName("chat_shared")
    val chatShared: ChatShared? = null
) : Convertible, Result {
    override fun toJson() = json.encodeToJsonElement(this)

    companion object {
        val FULL_MESSAGE = Message(
            messageId = 123456,
            user = FULL_USER,
            chat = FULL_CHAT,
            text = "some text",
            userShared = UserShared.NONE
        )
        val PART_MESSAGE = Message(
            messageId = 123456,
            chat = PART_CHAT,
            userShared = UserShared.NONE
        )
        val MESSAGE_FULL_JSON = JsonObject(
            mapOf(
                "message_id" to JsonPrimitive(123456),
                "from" to USER_FULL_JSON,
                "chat" to CHAT_FULL_JSON,
                "text" to JsonPrimitive("some text")
            )
        )
        val MESSAGE_PART_JSON = JsonObject(
            mapOf(
                "message_id" to JsonPrimitive(123456),
                "chat" to CHAT_PART_JSON
            )
        )
        val MESSAGE_REDUNDANT_PROPERTIES_JSON = JsonObject(
            mapOf(
                "message_id" to JsonPrimitive(123456),
                "chat" to CHAT_REDUNDANT_PROPERTIES_JSON,
                "redundant" to JsonPrimitive("redundant")
            )
        )
        val MESSAGE_FROM_FULL_JSON = json.decodeFromJsonElement<Message>(MESSAGE_FULL_JSON)
        val MESSAGE_FROM_PART_JSON = json.decodeFromJsonElement<Message>(MESSAGE_PART_JSON)
        val MESSAGE_FROM_REDUNDANT_PROPERTIES_JSON =
            json.decodeFromJsonElement<Message>(MESSAGE_REDUNDANT_PROPERTIES_JSON)
    }
}
