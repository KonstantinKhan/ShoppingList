package com.shopping_list.app.ktor.plugins

import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.modules.subclass
import ru.ktglib.transport.models.update.Update
import ru.ktglib.transport.models.update.UpdateWithMessage

val transportSerializersModule = SerializersModule {
    polymorphic(Update::class) {
        subclass(UpdateWithMessage::class)
    }
//    polymorphic(ReplyMarkup::class) {
//        subclass(InlineKeyboardMarkup::class)
//    }
}