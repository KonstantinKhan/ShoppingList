package com.shopping_list_bot.backend.shopping_list.logic.workers

import com.shopping_list_bot.common.context.BeContextShoppingList
import com.shopping_list_bot.common.models.`shopping-list`.ShoppingListModel
import ru.fit_changes.cor.CorChainDsl
import ru.fit_changes.cor.worker

fun CorChainDsl<BeContextShoppingList>.prepareForClean(title: String) = worker {
    this.title = title
    handle {
        shoppingList = ShoppingListModel(
            dbShoppingList.id,
            user = dbShoppingList.user,
            purchaseList = ArrayList()
        )
    }
}