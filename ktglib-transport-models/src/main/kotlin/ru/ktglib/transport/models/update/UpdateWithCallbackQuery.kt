package ru.ktglib.transport.models.update

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.decodeFromJsonElement
import kotlinx.serialization.json.encodeToJsonElement
import ru.ktglib.transport.models.CallbackQuery

import ru.ktglib.types.Json.json

@Serializable
data class UpdateWithCallbackQuery(
    @SerialName("update_id")
    override val updateId: Int,
    @SerialName("callback_query")
    val callbackQuery: CallbackQuery
) : Update() {
    override fun toJson() = json.encodeToJsonElement(this)

//    companion object {
//        val UPDATE_WITH_CALLBACK_QUERY = UpdateWithCallbackQuery(
//            updateId = 123456,
//            callbackQuery = FULL_CALLBACK_QUERY
//        )
//        val UPDATE_WITH_CALLBACK_QUERY_JSON = JsonObject(
//            mapOf(
//                "update_id" to JsonPrimitive(123456),
//                "callback_query" to CALLBACK_QUERY_FULL_JSON
//            )
//        )
//        val UPDATE_WITH_CALLBACK_QUERY_REDUNDANT_PROPERTIES_JSON = JsonObject(
//            mapOf(
//                "update_id" to JsonPrimitive(123456),
//                "callback_query" to CALLBACK_QUERY_FULL_JSON,
//                "redundant" to JsonPrimitive("redundant")
//            )
//        )
//        val UPDATE_WITH_CALLBACK_QUERY_FROM_JSON = json.decodeFromJsonElement<UpdateWithCallbackQuery>(
//            UPDATE_WITH_CALLBACK_QUERY_JSON
//        )
//        val UPDATE_WITH_CALLBACK_QUERY_FROM_REDUNDANT_PROPERTIES_JSON =
//            json.decodeFromJsonElement<UpdateWithCallbackQuery>(
//                UPDATE_WITH_CALLBACK_QUERY_REDUNDANT_PROPERTIES_JSON
//            )
//    }
}
