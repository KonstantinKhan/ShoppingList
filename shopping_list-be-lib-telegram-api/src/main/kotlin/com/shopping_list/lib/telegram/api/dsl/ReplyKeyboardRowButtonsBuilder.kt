package com.shopping_list.lib.telegram.api.dsl

import ru.ktglib.transport.models.KeyboardButton

fun ReplyKeyboardMarkupBuilder.row(block: ReplyKeyboardRowButtonsBuilder.() -> Unit) {
    add(ReplyKeyboardRowButtonsBuilder().apply(block))
}

@MessageDsl
class ReplyKeyboardRowButtonsBuilder(
    private val buttonBuilders: MutableList<ReplyKeyboardButtonBuilder> = mutableListOf()
) {
    fun add(replyKeyboardButtonBuilder: ReplyKeyboardButtonBuilder) = buttonBuilders.add(replyKeyboardButtonBuilder)
    fun build() = ArrayList<KeyboardButton>(buttonBuilders.map {
        it.build()
    })
}