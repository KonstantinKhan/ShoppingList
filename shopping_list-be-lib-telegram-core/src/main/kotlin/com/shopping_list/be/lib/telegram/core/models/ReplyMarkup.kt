package com.shopping_list.be.lib.telegram.core.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
sealed interface ReplyMarkup

@Serializable
data class InlineKeyboardMarkup(
    @SerialName("inline_keyboard")
    val inlineKeyboard: ArrayList<ArrayList<InlineKeyboardButton>>
): ReplyMarkup

@Serializable
data class InlineKeyboardButton(
    val text: String,
    @SerialName("callback_data")
    val callbackData: String
)


