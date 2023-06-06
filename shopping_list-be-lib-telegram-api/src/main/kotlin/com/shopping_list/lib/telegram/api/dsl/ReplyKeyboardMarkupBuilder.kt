package com.shopping_list.lib.telegram.api.dsl

import ru.ktglib.transport.models.ReplyKeyboardMarkup

fun replyKeyboardMarkup(block: ReplyKeyboardMarkupBuilder.() -> Unit) =
    ReplyKeyboardMarkupBuilder().apply(block).build()

@MessageDsl
class ReplyKeyboardMarkupBuilder(
    private val rowBuilders: MutableList<ReplyKeyboardRowButtonsBuilder> = mutableListOf(),
    var oneTimeKeyboard: Boolean? = null,
    var resizeKeyboard: Boolean? = null
) {

    fun add(replyKeyboardRowButtonBuilders: ReplyKeyboardRowButtonsBuilder) =
        rowBuilders.add(replyKeyboardRowButtonBuilders)

    fun build(): ReplyKeyboardMarkup = ReplyKeyboardMarkup(
        keyboard = ArrayList(rowBuilders.map {
            it.build()
        }),
        oneTimeKeyboard = oneTimeKeyboard,
        resizeKeyboard = resizeKeyboard
    )
}