package ru.ktglib.transport.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
sealed interface ReplyMarkup

@Serializable
data class InlineKeyboardMarkup(
    @SerialName("inline_keyboard")
    val inlineKeyboard: ArrayList<ArrayList<InlineKeyboardButton>>
) : ReplyMarkup

@Serializable
data class InlineKeyboardButton(
    val text: String,
    @SerialName("callback_data")
    val callbackData: String? = null
)

@Serializable
data class ReplyKeyboardMarkup(
    val keyboard: Array<Array<KeyboardButton>>,
)

@Serializable
data class KeyboardButton(
    val text: String,
    @SerialName("request_user")
    val requestUser: KeyboardButtonRequestUser? = null,
    @SerialName("request_chat")
    val requestChat: KeyboardButtonRequestChat? = null,
    @SerialName("request_contact")
    val requestContact: Boolean? = null,
    @SerialName("request_location")
    val requestLocation: Boolean? = null
)

@Serializable
data class KeyboardButtonRequestUser(
    @SerialName("request_id")
    val requestId: Int,
    @SerialName("request_is_bot")
    val userIsBot: Boolean? = null,
    @SerialName("user_is_premium")
    val userIsPremium: Boolean? = null
)

@Serializable
data class KeyboardButtonRequestChat(
    @SerialName("request_id")
    val requestId: Int,
    val chatIsChannel: Boolean
)
