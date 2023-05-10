package com.shopping_list.backend.mapping

import com.shopping_list_bot.common.context.BeContextShoppingList
import com.shopping_list_bot.common.models.MessageId
import com.shopping_list_bot.common.models.TgUser
import com.shopping_list_bot.common.models.UserId
import ru.ktglib.transport.models.update.UpdateWithCallbackQuery
import ru.ktglib.transport.models.update.UpdateWithMessage

fun BeContextShoppingList.setQuery(update: UpdateWithMessage) = apply {
    shoppingList = shoppingList.copy(user = update.toModelUser())
    purchase = update.message.text ?: ""
}

fun BeContextShoppingList.setQuery(update: UpdateWithCallbackQuery) = apply {
    shoppingList = shoppingList.copy(user = update.toModelUser())
    purchase = update.callbackQuery.data ?: ""
}

fun BeContextShoppingList.setMessageId(id: MessageId) = apply {
    messageId = id
}

private fun UpdateWithMessage.toModelUser() = this.message.user?.let {
    TgUser(
        userId = UserId(it.userId),
        firstName = it.firstName,
        lastName = it.lastName ?: "",
        userName = it.userName ?: ""
    )
} ?: TgUser.NONE

private fun UpdateWithCallbackQuery.toModelUser() = this.callbackQuery.user.let {
    TgUser(
        userId = UserId(it.userId),
        firstName = it.firstName,
        lastName = it.lastName ?: "",
        userName = it.userName ?: ""
    )
}
