package com.shopping_list.backend.shopping_list.service

import com.shopping_list.backend.mapping.config
import com.shopping_list.backend.mapping.setQuery
import com.shopping_list.backend.shopping_list.logic.ShoppingListProcessor
import com.shopping_list.common.context.BeContext
import ru.ktglib.transport.models.update.UpdateWithCallbackQuery
import ru.ktglib.transport.models.update.UpdateWithMessage

class ShoppingListService(
    private val processor: ShoppingListProcessor
) {
    suspend fun register(context: BeContext, update: UpdateWithMessage) {
        context.setQuery(update)
        processor.register(context)
    }

    suspend fun help(context: BeContext, update: UpdateWithMessage) {
        context.setQuery(update)
        processor.help(context)
    }

    suspend fun clear(context: BeContext, update: UpdateWithMessage) {
        context.setQuery(update)
        processor.clear(context)
    }

    suspend fun tidy(context: BeContext, update: UpdateWithMessage) {
        context.setQuery(update)
        processor.tidy(context)
    }

    suspend fun choose(context: BeContext, update: UpdateWithMessage) {
        context.setQuery(update)
        processor.choose(context)
    }

    suspend fun addPurchase(context: BeContext, update: UpdateWithMessage) {
        context.setQuery(update)
        processor.addPurchase(context)
    }

    suspend fun checkPurchase(context: BeContext, update: UpdateWithCallbackQuery) {
        context.setQuery(update)
        processor.checkPurchase(context)
    }

    suspend fun shareShoppingList(context: BeContext, update: UpdateWithMessage) {
        context.setQuery(update)
        processor.shareShoppingList(context)
    }

    suspend fun showRelatedShoppingLists(context: BeContext, update: UpdateWithMessage) {
        context.setQuery(update)
        processor.showRelatedShoppingLists(context)
    }

    suspend fun handleMessage(context: BeContext, update: UpdateWithMessage) {
        context.config(update)
        processor.handleMessage(context)
    }

    suspend fun handleCallbackData(context: BeContext, update: UpdateWithCallbackQuery) {
        context.config(update)
        processor.handleCallbackData(context)
    }

    suspend fun detach(context: BeContext, update: UpdateWithMessage) {
        context.setQuery(update)
        processor.detach(context)
    }

    suspend fun create(context: BeContext, update: UpdateWithMessage) {
        context.config(update)
        processor.create(context)
    }
    suspend fun delete(context: BeContext, update: UpdateWithMessage) {
        context.config(update)
        processor.delete(context)
    }

    suspend fun update(context: BeContext, update: UpdateWithMessage) {
        context.config(update)
        processor.update(context)
    }
}