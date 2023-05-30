package com.shopping_list_bot.backend.shopping_list.logic

import com.shopping_list_bot.backend.shopping_list.logic.`shopping-list`.*
import com.shopping_list_bot.common.context.BeContextShoppingList
import com.shopping_list_bot.common.context.ShoppingListContextConfig

class ShoppingListProcessor(private val config: ShoppingListContextConfig = ShoppingListContextConfig()) {

    suspend fun register(context: BeContextShoppingList) {
        ShoppingListRegister.exec(context.initSettings())
    }

    suspend fun help(context: BeContextShoppingList) {
        ShoppingListHelp.exec(context.initSettings())
    }

    suspend fun clear(context: BeContextShoppingList) {
        ShoppingListClear.exec(context.initSettings())
    }

    suspend fun tidy(context: BeContextShoppingList) {
        ShoppingListTidy.exec(context.initSettings())
    }

    suspend fun create(context: BeContextShoppingList) {
        ShoppingListCreate.exec(context.initSettings())
    }

    suspend fun addPurchase(context: BeContextShoppingList) {
        AddPurchase.exec(context.initSettings())
    }

    suspend fun checkPurchase(context: BeContextShoppingList) {
        ShoppingListCheckPurchase.exec(context.initSettings())
    }

    suspend fun shareShoppingList(context: BeContextShoppingList) {
        ShareShoppingList.exec(context.initSettings())
    }

    suspend fun showRelatedShoppingLists(context: BeContextShoppingList) {
        ShowRelatedShoppingLists.exec(context.initSettings())
    }

    private fun BeContextShoppingList.initSettings() = apply {
        config = this@ShoppingListProcessor.config
    }
}