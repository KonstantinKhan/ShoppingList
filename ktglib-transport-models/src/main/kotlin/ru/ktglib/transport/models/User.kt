package ru.ktglib.transport.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.*
import ru.ktglib.transport.models.Json.json

@Serializable
data class User(
    @SerialName("id")
    val userId: Int,
    @SerialName("is_bot")
    val isBot: Boolean,
    @SerialName("first_name")
    val firstName: String,
    @SerialName("last_name")
    val lastName: String? = null,
    @SerialName("username")
    val userName: String? = null
) : Convertible {

    override fun toJson() = json.encodeToJsonElement(this)

    companion object {
        val FULL_USER =
            User(userId = 123456, isBot = false, firstName = "first", lastName = "last", userName = "name")
        val PART_USER = User(userId = 123456, isBot = false, firstName = "first")

        val USER_FULL_JSON = JsonObject(
            mapOf(
                "id" to JsonPrimitive(123456),
                "is_bot" to JsonPrimitive(false),
                "first_name" to JsonPrimitive("first"),
                "last_name" to JsonPrimitive("last"),
                "username" to JsonPrimitive("name")
            )
        )
        val USER_PART_JSON = JsonObject(
            mapOf(
                "id" to JsonPrimitive(123456),
                "is_bot" to JsonPrimitive(false),
                "first_name" to JsonPrimitive("first"),
            )
        )
        val USER_REDUNDANT_PROPERTIES_JSON = JsonObject(
            mapOf(
                "id" to JsonPrimitive(123456),
                "is_bot" to JsonPrimitive(false),
                "first_name" to JsonPrimitive("first"),
                "redundant" to JsonPrimitive("redundant")
            )
        )
        val USER_FROM_FULL_JSON = json.decodeFromJsonElement<User>(USER_FULL_JSON)
        val USER_FROM_PART_JSON = json.decodeFromJsonElement<User>(USER_PART_JSON)
        val USER_FROM_REDUNDANT_PROPERTIES_JSON = json.decodeFromJsonElement<User>(USER_REDUNDANT_PROPERTIES_JSON)
    }
}
