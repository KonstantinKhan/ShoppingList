package com.shopping_list.backend.shopping_list.logic.workers

import com.shopping_list.common.context.BeContext
import com.shopping_list.repo.shopping_list.DbShoppingListIdRequest
import com.shopping_list.repo.shopping_list.DbStateRequest
import ru.fit_changes.cor.CorChainDsl
import ru.fit_changes.cor.worker

fun CorChainDsl<BeContext>.deleteChecked(title: String) = worker {
    this.title = title
    handle {
        shoppingListRepo.deleteCheckedPurchases(
            DbStateRequest(
                shoppingListId = shoppingList.id
            )
        ).result.let {
            shoppingList = shoppingList.copy(purchaseList = it.purchaseList)
        }
        shoppingListRepo.readSharedData(DbShoppingListIdRequest(shoppingList.id)).sharedShoppingLists.forEach {
            shoppingListRepo.deleteCheckedPurchases(DbStateRequest(shoppingListId = it.id))
        }
    }
}