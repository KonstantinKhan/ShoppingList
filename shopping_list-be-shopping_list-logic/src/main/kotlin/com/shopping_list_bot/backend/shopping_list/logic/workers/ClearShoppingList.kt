package com.shopping_list_bot.backend.shopping_list.logic.workers

import com.shopping_list_bot.common.context.BeContextShoppingList
import com.shopping_list_bot.repo.shopping_list.DbStateContextRequest
import ru.fit_changes.cor.CorChainDsl
import ru.fit_changes.cor.worker

fun CorChainDsl<BeContextShoppingList>.clearShoppingList(title: String) = worker {
    this.title = title
    handle {
        shoppingListRepo.clearShoppingList(
            DbStateContextRequest(
                shoppingListId = shoppingList.id
            )
        ).result.let { shoppingList = shoppingList.copy(purchaseList = it.purchaseList) }
    }
}