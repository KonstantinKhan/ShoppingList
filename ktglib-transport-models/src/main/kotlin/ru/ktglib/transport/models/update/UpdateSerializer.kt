package ru.ktglib.transport.models.update

import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.json.JsonContentPolymorphicSerializer
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.jsonObject

object UpdateSerializer : JsonContentPolymorphicSerializer<Update>(Update::class) {
    override fun selectDeserializer(element: JsonElement): DeserializationStrategy<Update> {
        element.jsonObject.let {
            when {
                it.containsKey("message") -> return UpdateWithMessage.serializer()
                it.containsKey("callback_query") -> return UpdateWithCallbackQuery.serializer()
                else -> throw Error("Unknown type")
            }
        }
    }
}