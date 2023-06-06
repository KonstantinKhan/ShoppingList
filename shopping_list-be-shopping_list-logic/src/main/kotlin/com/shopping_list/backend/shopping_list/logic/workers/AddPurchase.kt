package com.shopping_list.backend.shopping_list.logic.workers

import com.shopping_list.common.context.BeContext
import com.shopping_list.common.models.shopping_list.PurchaseModel
import com.shopping_list.repo.shopping_list.DbPurchaseModelRequest
import ru.fit_changes.cor.CorChainDsl
import ru.fit_changes.cor.worker

fun CorChainDsl<BeContext>.addPurchase(title: String) = worker {
    this.title = title
    handle {
        shoppingListRepo.createPurchase(
            DbPurchaseModelRequest(
                shoppingList.id,
                purchase = purchaseList.map { PurchaseModel(it, false) }
            )
        ).result.let {
            shoppingList = dbShoppingList.copy(purchaseList = it.purchaseList)
        }
    }
}