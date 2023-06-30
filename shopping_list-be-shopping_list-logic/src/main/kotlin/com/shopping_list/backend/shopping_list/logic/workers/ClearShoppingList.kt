package com.shopping_list.backend.shopping_list.logic.workers

import com.shopping_list.common.context.BeContext
import com.shopping_list.repo.shopping_list.DbShoppingListIdRequest
import com.shopping_list.repo.shopping_list.DbStateRequest
import ru.fit_changes.cor.CorChainDsl
import ru.fit_changes.cor.worker

fun CorChainDsl<BeContext>.clearShoppingList(title: String) = worker {
    this.title = title
    handle {
        shoppingListRepo.clearShoppingList(
            DbStateRequest(
                shoppingListId = shoppingList.id
            )
        ).result.let { shoppingList = shoppingList.copy(purchaseList = it.purchaseList) }
        shoppingListRepo.readSharedData(DbShoppingListIdRequest(shoppingList.id)).sharedShoppingLists.forEach {
            shoppingListRepo.clearShoppingList(DbStateRequest(shoppingListId = it.id))
        }
    }
}