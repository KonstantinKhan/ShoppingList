package ru.shopping_list.sender_service

import com.shopping_list.response.ResponseWithMessage
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.modules.subclass
import com.shopping_list.response.Response

fun jsonHelper() = Json {
    serializersModule = module
    isLenient = true
    ignoreUnknownKeys = true
}

val module = SerializersModule {
    polymorphic(Response::class){
        subclass(ResponseWithMessage::class)
    }
}