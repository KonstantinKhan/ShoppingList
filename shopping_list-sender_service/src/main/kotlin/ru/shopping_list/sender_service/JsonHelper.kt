package ru.shopping_list.sender_service

import ru.ktglib.types.ResponseWithMessage
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.modules.plus
import kotlinx.serialization.modules.subclass
import ru.ktglib.types.Message
import ru.ktglib.types.Result
import ru.ktglib.types.User

//fun jsonHelper() = Json {
//    serializersModule = responseModule + resultModule
//    isLenient = true
//    ignoreUnknownKeys = true
//}

//val responseModule = SerializersModule {
//    polymorphic(Response::class) {
//        subclass(ResponseWithMessage.serializer())
//    }
//}
val resultModule = SerializersModule {
    polymorphic(Result::class) {
        subclass(Message::class)
        subclass(User::class)
    }
}