package com.shopping_list_bot.backend.shopping_list.logic.workers

import com.shopping_list_bot.common.context.BeContextShoppingList
import com.shopping_list_bot.repo.shopping_list.DbPurchaseRequest
import ru.fit_changes.cor.CorChainDsl
import ru.fit_changes.cor.worker

fun CorChainDsl<BeContextShoppingList>.checkPurchase(title: String) = worker {
    this.title = title
    handle {
        shoppingListRepo.togglePurchase(
            DbPurchaseRequest(
                purchaseList,
                dbShoppingList.id,
                dbShoppingList.user.userId
            )
        ).result?.let {
            shoppingList = dbShoppingList.copy(purchaseList = it.purchaseList) }
    }
}