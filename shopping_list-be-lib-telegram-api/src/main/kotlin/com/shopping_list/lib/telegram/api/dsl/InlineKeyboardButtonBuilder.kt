package com.shopping_list.lib.telegram.api.dsl

import ru.ktglib.transport.models.InlineKeyboardButton

fun InlineKeyboardRowButtonsBuilder.button(block: InlineKeyboardButtonBuilder.() -> Unit) {
    add(InlineKeyboardButtonBuilder().apply(block))
}

@MessageDsl
class InlineKeyboardButtonBuilder(
    var text: String = "",
    var callbackData: String = ""
) {
    fun build(): InlineKeyboardButton = InlineKeyboardButton(text, callbackData)
}
