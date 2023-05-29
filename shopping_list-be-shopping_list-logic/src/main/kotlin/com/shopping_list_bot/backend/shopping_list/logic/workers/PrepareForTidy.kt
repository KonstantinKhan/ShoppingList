package com.shopping_list_bot.backend.shopping_list.logic.workers

import com.shopping_list_bot.common.context.BeContextShoppingList
import com.shopping_list_bot.common.models.shopping_list.ShoppingListModel
import ru.fit_changes.cor.CorChainDsl
import ru.fit_changes.cor.worker

fun CorChainDsl<BeContextShoppingList>.prepareForTidy(title: String) = worker {
    this.title = title
    handle {
        with(dbShoppingList) {
            shoppingList = ShoppingListModel(
                id,
                user = user,
                purchaseList = ArrayList(purchaseList.filter { !it.checked })
            )
        }
    }
}