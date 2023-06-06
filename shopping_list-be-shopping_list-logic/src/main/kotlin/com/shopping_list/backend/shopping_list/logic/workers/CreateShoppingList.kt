package com.shopping_list.backend.shopping_list.logic.workers

import com.shopping_list.common.context.BeContext
import com.shopping_list.common.models.shopping_list.ShoppingListId
import com.shopping_list.repo.shopping_list.DbShoppingListRequest
import ru.fit_changes.cor.CorChainDsl
import ru.fit_changes.cor.worker

fun CorChainDsl<BeContext>.createShoppingList(title: String) = worker {
    this.title = title
    on {
        shoppingList.id == ShoppingListId.NONE
    }
    handle {
        shoppingListRepo.createShoppingList(
            DbShoppingListRequest(shoppingList)
        ).result?.let {
            shoppingList = it
        }
    }
}