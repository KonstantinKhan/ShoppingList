package com.shopping_list.backend.shopping_list.logic.workers

import com.shopping_list.common.context.BeContext
import com.shopping_list.repo.shopping_list.DbSharedShoppingListRequest
import ru.fit_changes.cor.CorChainDsl
import ru.fit_changes.cor.worker

fun CorChainDsl<BeContext>.shareShoppingList(title: String) = worker {
    this.title = title
    on { errors.isEmpty() }
    handle {
        with(
            shoppingListRepo.shareShoppingList(
                DbSharedShoppingListRequest(
                    shoppingList.id,
                    recipient,
                    shoppingListsOfRecipient
                )
            )
        ) {
            error?.let { errors.add(it) }
                ?: result.let { shoppingList = shoppingList.copy(purchaseList = it.purchaseList) }
        }
    }
}