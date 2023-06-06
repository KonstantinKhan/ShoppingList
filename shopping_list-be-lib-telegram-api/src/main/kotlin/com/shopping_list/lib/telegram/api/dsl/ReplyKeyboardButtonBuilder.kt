package com.shopping_list.lib.telegram.api.dsl

import ru.ktglib.transport.models.KeyboardButton
import ru.ktglib.transport.models.KeyboardButtonRequestChat
import ru.ktglib.transport.models.KeyboardButtonRequestUser

fun ReplyKeyboardRowButtonsBuilder.button(block: ReplyKeyboardButtonBuilder.() -> Unit) {
    add(ReplyKeyboardButtonBuilder().apply(block))
}

class ReplyKeyboardButtonBuilder(
    var text: String = "",
    var requestUser: KeyboardButtonRequestUser? = KeyboardButtonRequestUser(
        requestId = 0, userIsBot = false, userIsPremium = null
    ),
    var requestChat: KeyboardButtonRequestChat? = null,
    var requestContact: Boolean? = null,
    var requestLocation: Boolean? = null
) {
    fun build() = KeyboardButton(text, requestUser, requestChat, requestContact, requestLocation)
}