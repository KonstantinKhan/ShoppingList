package com.shopping_list_bot.backend.shopping_list.logic.workers

import com.shopping_list_bot.common.context.BeContextShoppingList
import com.shopping_list_bot.common.models.PurchaseModel
import com.shopping_list_bot.repo.shopping_list.DbPurchaseModelRequest
import ru.fit_changes.cor.CorChainDsl
import ru.fit_changes.cor.worker

fun CorChainDsl<BeContextShoppingList>.addPurchase(title: String) = worker {
    this.title = title
    handle {
        shoppingList = dbShoppingList
        shoppingListRepo.addPurchase(
            DbPurchaseModelRequest(
                shoppingList.id,
                purchase = PurchaseModel(purchase, checked = false)
            )
        ).result?.let {
            shoppingList = shoppingList.copy(purchaseList = it.purchaseList)
        }
        println("shoppingList: $shoppingList")
    }
}