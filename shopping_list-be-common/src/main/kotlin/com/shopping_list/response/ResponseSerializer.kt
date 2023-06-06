package com.shopping_list.response

import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.json.JsonContentPolymorphicSerializer
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.jsonObject

object ResponseSerializer : JsonContentPolymorphicSerializer<Response>(Response::class) {
    override fun selectDeserializer(element: JsonElement): DeserializationStrategy<Response> = when {
        "result" in element.jsonObject -> when {
            element.jsonObject["result"]?.jsonObject?.let { result ->
                "message_id" in result && "date" in result && "chat" in result
            } == true -> ResponseWithMessage.serializer()

            else -> throw Error("Unknown type")
        }

        "error_code" in element.jsonObject -> {
            ResponseWithError.serializer()
        }

        else -> throw Error("Unknown type")
    }
}