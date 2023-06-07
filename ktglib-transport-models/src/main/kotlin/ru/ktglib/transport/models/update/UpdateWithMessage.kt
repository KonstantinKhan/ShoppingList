package ru.ktglib.transport.models.update

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.decodeFromJsonElement
import kotlinx.serialization.json.encodeToJsonElement
import ru.ktglib.types.Json.json
import ru.ktglib.types.Message


@Serializable
data class UpdateWithMessage(
    @SerialName("update_id")
    override val updateId: Int,
    val message: Message
) : Update() {
    override fun toJson() = json.encodeToJsonElement(this)

//    companion object {
//        val UPDATE_WITH_MESSAGE = UpdateWithMessage(
//            updateId = 123456,
//            message = FULL_MESSAGE
//        )
//        val UPDATE_WITH_MESSAGE_JSON = JsonObject(
//            mapOf(
//                "update_id" to JsonPrimitive(123456),
//                "message" to MESSAGE_FULL_JSON
//            )
//        )
//        val UPDATE_WITH_MESSAGE_REDUNDANT_PROPERTIES_JSON = JsonObject(
//            mapOf(
//                "update_id" to JsonPrimitive(123456),
//                "message" to MESSAGE_FULL_JSON,
//                "redundant" to JsonPrimitive("redundant")
//            )
//        )
//        val UPDATE_WITH_MESSAGE_FROM_JSON = json.decodeFromJsonElement<UpdateWithMessage>(UPDATE_WITH_MESSAGE_JSON)
//        val UPDATE_WITH_MESSAGE_FROM_REDUNDANT_PROPERTIES_JSON = json.decodeFromJsonElement<UpdateWithMessage>(UPDATE_WITH_MESSAGE_REDUNDANT_PROPERTIES_JSON)
//    }
}
