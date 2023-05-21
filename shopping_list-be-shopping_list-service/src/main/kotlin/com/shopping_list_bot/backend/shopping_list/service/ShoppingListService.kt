package com.shopping_list_bot.backend.shopping_list.service

import com.shopping_list.backend.mapping.setQuery
import com.shopping_list_bot.backend.shopping_list.logic.ShoppingListProcessor
import com.shopping_list_bot.common.context.BeContextShoppingList
import ru.ktglib.transport.models.update.UpdateWithCallbackQuery
import ru.ktglib.transport.models.update.UpdateWithMessage

class ShoppingListService(
    private val processor: ShoppingListProcessor
) {
    suspend fun register(context: BeContextShoppingList, update: UpdateWithMessage) {
        context.setQuery(update)
        processor.register(context)
    }

    suspend fun help(context: BeContextShoppingList, update: UpdateWithMessage) {
        context.setQuery(update)
        processor.help(context)
    }

    suspend fun clear(context: BeContextShoppingList, update: UpdateWithMessage) {
        context.setQuery(update)
        processor.clear(context)
    }

    suspend fun tidy(context: BeContextShoppingList, update: UpdateWithMessage) {
        context.setQuery(update)
        processor.tidy(context)
    }

    suspend fun addPurchase(context: BeContextShoppingList, update: UpdateWithMessage) {
        context.setQuery(update)
        processor.addPurchase(context)
    }

    suspend fun checkPurchase(context: BeContextShoppingList, update: UpdateWithCallbackQuery) {
        context.setQuery(update)
        processor.checkPurchase(context)
    }

    suspend fun shareShoppingList(context: BeContextShoppingList, update: UpdateWithMessage) {
        context.setQuery(update)
        processor.shareShoppingList(context)
    }
}