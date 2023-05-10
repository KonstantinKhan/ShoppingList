package ru.ktglib.transport.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.*
import ru.ktglib.transport.models.Json.json

@Serializable
data class Chat(
    @SerialName("id")
    val chatId: Int,
    @SerialName("first_name")
    val firstName: String? = null,
    @SerialName("last_name")
    val lastName: String? = null,
    @SerialName("username")
    val userName: String? = null,
    val type: String
) : Convertible {
    override fun toJson() = json.encodeToJsonElement(this)

    companion object {
        val FULL_CHAT =
            Chat(
                chatId = 123456,
                firstName = "first",
                lastName = "last",
                userName = "name",
                type = ChatType.PRIVATE.type
            )
        val PART_CHAT = Chat(chatId = 123456, firstName = "first", type = ChatType.PRIVATE.type)

        val CHAT_FULL_JSON = JsonObject(
            mapOf(
                "id" to JsonPrimitive(123456),
                "first_name" to JsonPrimitive("first"),
                "last_name" to JsonPrimitive("last"),
                "username" to JsonPrimitive("name"),
                "type" to JsonPrimitive("private")
            )
        )

        val CHAT_PART_JSON = JsonObject(
            mapOf(
                "id" to JsonPrimitive(123456),
                "first_name" to JsonPrimitive("first"),
                "type" to JsonPrimitive("private")
            )
        )

        val CHAT_REDUNDANT_PROPERTIES_JSON = JsonObject(
            mapOf(
                "id" to JsonPrimitive(123456),
                "first_name" to JsonPrimitive("first"),
                "type" to JsonPrimitive("private"),
                "redundant" to JsonPrimitive("redundant")
            )
        )
        val CHAT_FROM_FULL_JSON = json.decodeFromJsonElement<Chat>(CHAT_FULL_JSON)
        val CHAT_FROM_PART_JSON = json.decodeFromJsonElement<Chat>(CHAT_PART_JSON)
        val CHAT_FROM_REDUNDANT_PROPERTIES_JSON = json.decodeFromJsonElement<Chat>(CHAT_REDUNDANT_PROPERTIES_JSON)
    }
}
