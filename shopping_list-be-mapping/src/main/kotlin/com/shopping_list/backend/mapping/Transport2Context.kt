package com.shopping_list.backend.mapping

import com.shopping_list.common.context.BeContext
import com.shopping_list.common.models.MessageId
import com.shopping_list.common.models.User
import com.shopping_list.common.models.UserId
import ru.ktglib.transport.models.update.UpdateWithCallbackQuery
import ru.ktglib.transport.models.update.UpdateWithMessage

fun BeContext.setQuery(update: UpdateWithMessage) = apply {
    shoppingList = shoppingList.copy(user = update.toModelUser())
    purchaseList = update.message.text?.lines() ?: emptyList()
    recipient = update.message.userShared?.let { User(UserId(it.userId), "") } ?: User.NONE
}

fun BeContext.setQuery(update: UpdateWithCallbackQuery) = apply {
    shoppingList = shoppingList.copy(user = update.toModelUser())
    messageText = update.callbackQuery.data ?: ""
}

fun BeContext.setMessageId(id: MessageId) = apply {
    messageId = id
}

fun BeContext.config(update: UpdateWithMessage) = apply {
    shoppingList = shoppingList.copy(user = update.toModelUser())
    messageText = update.message.text ?: ""
    recipient = update.message.userShared?.let { User(UserId(it.userId), "") } ?: User.NONE
}

fun BeContext.config(update: UpdateWithCallbackQuery) = apply {
    shoppingList = shoppingList.copy(user = update.toModelUser())
    messageText = update.callbackQuery.data ?: ""
    queryId = update.callbackQuery.queryId
}

private fun UpdateWithMessage.toModelUser() = this.message.user?.let {
    User(
        userId = UserId(it.userId),
        firstName = it.firstName,
        lastName = it.lastName ?: "",
        userName = it.userName ?: ""
    )
} ?: User.NONE

private fun UpdateWithCallbackQuery.toModelUser() = this.callbackQuery.user.let {
    User(
        userId = UserId(it.userId),
        firstName = it.firstName,
        lastName = it.lastName ?: "",
        userName = it.userName ?: ""
    )
}
