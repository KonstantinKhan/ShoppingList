package com.shopping_list.lib.telegram.api.dsl

import ru.ktglib.transport.models.InlineKeyboardButton


fun InlineKeyboardMarkupBuilder.row(block: InlineKeyboardRowButtonsBuilder.() -> Unit) {
    add(InlineKeyboardRowButtonsBuilder().apply(block))
}

@MessageDsl
class InlineKeyboardRowButtonsBuilder(
    private val buttonBuilders: MutableList<InlineKeyboardButtonBuilder> = mutableListOf()
) {

    fun add(inlineKeyboardButtonBuilder: InlineKeyboardButtonBuilder) =
        buttonBuilders.add(inlineKeyboardButtonBuilder)

    fun build(): ArrayList<InlineKeyboardButton> = ArrayList(buttonBuilders.map { it.build() })
}