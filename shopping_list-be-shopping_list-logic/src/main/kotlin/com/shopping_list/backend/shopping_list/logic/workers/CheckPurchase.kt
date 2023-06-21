package com.shopping_list.backend.shopping_list.logic.workers

import com.shopping_list.common.context.BeContext
import com.shopping_list.repo.shopping_list.DbShoppingListIdRequest
import com.shopping_list.repo.shopping_list.DbShoppingListRequest
import ru.fit_changes.cor.CorChainDsl
import ru.fit_changes.cor.worker

fun CorChainDsl<BeContext>.checkPurchase(title: String) = worker {
    this.title = title
    handle {

        shoppingListRepo.readSharedData(DbShoppingListIdRequest(shoppingList.id)).sharedShoppingLists.takeIf {
            it.isNotEmpty()
        }?.let { lists ->
            lists.forEach { list ->
                shoppingListRepo.updateShoppingList(
                    DbShoppingListRequest(
                        list,
                        dbShoppingList.purchaseList.filter { messageText.lines().contains(it.name) }
                            .map { it.copy(checked = !it.checked) }
                    ))
            }
        }

        shoppingListRepo.updateShoppingList(
            DbShoppingListRequest(
                dbShoppingList,
                dbShoppingList.purchaseList.filter { messageText.lines().contains(it.name) }
                    .map { it.copy(checked = !it.checked) }
            )).result.let {
            dbShoppingList = it
        }
    }
}