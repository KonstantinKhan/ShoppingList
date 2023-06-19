package com.shopping_list.backend.shopping_list.logic

import com.shopping_list.backend.shopping_list.logic.shopping_list.*
import com.shopping_list.common.context.BeContext
import com.shopping_list.common.context.ShoppingListContextConfig

class ShoppingListProcessor(private val config: ShoppingListContextConfig = ShoppingListContextConfig()) {

    suspend fun register(context: BeContext) {
        ShoppingListRegister.exec(context.initSettings())
    }

    suspend fun help(context: BeContext) {
        ShoppingListHelp.exec(context.initSettings())
    }

    suspend fun clear(context: BeContext) {
        ShoppingListClear.exec(context.initSettings())
    }

    suspend fun tidy(context: BeContext) {
        ShoppingListTidy.exec(context.initSettings())
    }

    suspend fun choose(context: BeContext) {
        ShoppingListChoose.exec(context.initSettings())
    }

    suspend fun create(context: BeContext) {
        ShoppingListCreate.exec(context.initSettings())
    }

    suspend fun addPurchase(context: BeContext) {
        AddPurchase.exec(context.initSettings())
    }

    suspend fun checkPurchase(context: BeContext) {
        ShoppingListCheckPurchase.exec(context.initSettings())
    }

    suspend fun shareShoppingList(context: BeContext) {
        ShareShoppingList.exec(context.initSettings())
    }

    suspend fun showRelatedShoppingLists(context: BeContext) {
        ShowRelatedShoppingLists.exec(context.initSettings())
    }

    suspend fun handleMessage(context: BeContext) {
        HandleMessage.exec(context.initSettings())
    }

    suspend fun handleCallbackData(context: BeContext) {
        HandleCallbackData.exec(context.initSettings())
    }

    private fun BeContext.initSettings() = apply {
        config = this@ShoppingListProcessor.config
    }
}