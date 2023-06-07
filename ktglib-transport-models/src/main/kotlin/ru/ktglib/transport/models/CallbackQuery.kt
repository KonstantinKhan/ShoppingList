package ru.ktglib.transport.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.decodeFromJsonElement
import kotlinx.serialization.json.encodeToJsonElement
import ru.ktglib.types.Json.json
import ru.ktglib.types.Convertible
import ru.ktglib.types.Json
import ru.ktglib.types.User


@Serializable
data class CallbackQuery(
    @SerialName("id")
    val queryId: String,
    @SerialName("from")
    val user: User,
    val data: String? = null
) : Convertible {
    override fun toJson() = Json.json.encodeToJsonElement(this)

//    companion object {
//        val FULL_CALLBACK_QUERY = CallbackQuery(
//            queryId = "123456",
//            user = FULL_USER,
//            data = "some data"
//        )
//        val PART_CALLBACK_QUERY = CallbackQuery(
//            queryId = "123456",
//            user = PART_USER
//        )
//        val CALLBACK_QUERY_FULL_JSON = JsonObject(
//            mapOf(
//                "id" to JsonPrimitive("123456"),
//                "from" to USER_FULL_JSON,
//                "data" to JsonPrimitive("some data")
//            )
//        )
//        val CALLBACK_QUERY_PART_JSON = JsonObject(
//            mapOf(
//                "id" to JsonPrimitive("123456"),
//                "from" to USER_PART_JSON
//            )
//        )
//        val CALLBACK_QUERY_REDUNDANT_PROPERTIES_JSON = JsonObject(
//            mapOf(
//                "id" to JsonPrimitive("123456"),
//                "from" to USER_REDUNDANT_PROPERTIES_JSON,
//                "redundant" to JsonPrimitive("redundant")
//            )
//        )
//        val CALLBACK_QUERY_FROM_FULL_JSON = json.decodeFromJsonElement<CallbackQuery>(CALLBACK_QUERY_FULL_JSON)
//        val CALLBACK_QUERY_FROM_PART_JSON = json.decodeFromJsonElement<CallbackQuery>(CALLBACK_QUERY_PART_JSON)
//        val CALLBACK_QUERY_FROM_REDUNDANT_PROPERTIES_JSON = json.decodeFromJsonElement<CallbackQuery>(
//            CALLBACK_QUERY_REDUNDANT_PROPERTIES_JSON
//        )
//    }
}
