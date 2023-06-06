package com.shopping_list.lib.telegram.api.dsl

import ru.ktglib.transport.models.InlineKeyboardMarkup


fun inlineKeyboardMarkup(block: InlineKeyboardMarkupBuilder.() -> Unit) =
    InlineKeyboardMarkupBuilder().apply(block).build()

@MessageDsl
class InlineKeyboardMarkupBuilder(
    private val rowBuilders: MutableList<InlineKeyboardRowButtonsBuilder> = mutableListOf()
) {

    fun add(inlineKeyboardRowButtons: InlineKeyboardRowButtonsBuilder) =
        rowBuilders.add(inlineKeyboardRowButtons)

    fun build(): InlineKeyboardMarkup = InlineKeyboardMarkup(
        inlineKeyboard = ArrayList(rowBuilders.map { it.build() })
    )
}