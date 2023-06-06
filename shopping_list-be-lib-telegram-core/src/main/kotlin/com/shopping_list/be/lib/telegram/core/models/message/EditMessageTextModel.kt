package com.shopping_list.be.lib.telegram.core.models.message

import com.shopping_list.be.lib.telegram.core.models.InlineKeyboardMarkup
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EditMessageTextModel(
    @SerialName("chat_id")
    val chatId: Int? = null,
    @SerialName("message_id")
    val messageId: Int? = null,
    @SerialName("inline_message_id")
    val inlineMessageId: String? = null,
    val text: String,
    @SerialName("parse_mode")
    val parseMode: String? = null,
    val entities: Array<MessageEntity>? = null,
    @SerialName("disable_web_page_preview")
    val disableWebPagePreview: Boolean? = null,
    @SerialName("reply_markup")
    val replyMarkup: InlineKeyboardMarkup? = null
)
