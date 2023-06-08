package ru.ktglib.types

import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.json.JsonContentPolymorphicSerializer
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.jsonObject

object ResultSerializer : JsonContentPolymorphicSerializer<Result>(Result::class) {
    override fun selectDeserializer(element: JsonElement): DeserializationStrategy<Result> = when {
//        element.jsonObject.let { result ->
//            "message_id" in result && "date" in result && "chat" in result
//        } -> {
//            Message.serializer()
//        }

        element.jsonObject.let { result ->
            "message_id" in result
        } -> {
            println("message...")
            Message.serializer()
        }

        element.jsonObject.let { result ->
            "id" in result && "is_bot" in result && "first_name" in result
        } -> {
            println("user...")
            User.serializer()
        }

        else -> throw Error("Unknown type")
    }
}