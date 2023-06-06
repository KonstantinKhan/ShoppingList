package com.shopping_list.be.lib.telegram.core.models

import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.modules.subclass

val transportSerializersModule = SerializersModule {
    polymorphic(Update::class) {
        subclass(UpdateWithMessage::class)
        subclass(UpdateWithInlineQuery::class)
    }
    polymorphic(ReplyMarkup::class) {
        subclass(InlineKeyboardMarkup::class)
    }
}