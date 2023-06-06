package com.shopping_list.be.lib.telegram.core.models

import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonContentPolymorphicSerializer
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.jsonObject

@Serializable(with = UpdateSerializer::class)
abstract class Update {
    @SerialName("update_id")
    abstract val updateId: Int
}

@Serializable
data class UpdateWithMessage(
    @SerialName("update_id")
    override val updateId: Int,
    val message: MessageResponse
) : Update()

@Serializable
data class UpdateWithInlineQuery(
    @SerialName("update_id")
    override val updateId: Int,
    @SerialName("inline_query")
    val inlineQuery: InlineQuery
) : Update()

@Serializable
data class UpdateWithCallbackQuery(
    @SerialName("update_id")
    override val updateId: Int,
    @SerialName("callback_query")
    val callbackQuery: CallbackQuery
) : Update()

object UpdateSerializer : JsonContentPolymorphicSerializer<Update>(Update::class) {
    override fun selectDeserializer(element: JsonElement): DeserializationStrategy<out Update> {
        element.jsonObject.let {
            when {
                it.containsKey("message") -> return UpdateWithMessage.serializer()
                it.containsKey("inline_query") -> return UpdateWithInlineQuery.serializer()
                it.containsKey("callback_query") -> return UpdateWithCallbackQuery.serializer()
                else -> throw Error("Unknown type")
            }
        }
    }
}

