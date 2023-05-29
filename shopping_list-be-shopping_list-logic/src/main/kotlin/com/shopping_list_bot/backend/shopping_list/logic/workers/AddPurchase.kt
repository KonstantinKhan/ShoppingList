package com.shopping_list_bot.backend.shopping_list.logic.workers

import com.shopping_list_bot.common.context.BeContextShoppingList
import com.shopping_list_bot.common.models.shopping_list.PurchaseModel
import com.shopping_list_bot.repo.shopping_list.DbPurchaseModelRequest
import ru.fit_changes.cor.CorChainDsl
import ru.fit_changes.cor.worker

fun CorChainDsl<BeContextShoppingList>.addPurchase(title: String) = worker {
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