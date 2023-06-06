package com.shopping_list.backend.shopping_list.logic.workers

import com.shopping_list.common.context.BeContext
import com.shopping_list.repo.shopping_list.DbPurchaseRequest
import ru.fit_changes.cor.CorChainDsl
import ru.fit_changes.cor.worker

fun CorChainDsl<BeContext>.checkPurchase(title: String) = worker {
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