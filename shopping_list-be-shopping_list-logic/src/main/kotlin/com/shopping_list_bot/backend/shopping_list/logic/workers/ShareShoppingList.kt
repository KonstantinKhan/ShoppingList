package com.shopping_list_bot.backend.shopping_list.logic.workers

import com.shopping_list_bot.common.context.BeContextShoppingList
import com.shopping_list_bot.repo.shopping_list.DbSharedShoppingListRequest
import ru.fit_changes.cor.CorChainDsl
import ru.fit_changes.cor.worker

fun CorChainDsl<BeContextShoppingList>.shareShoppingList(title: String) = worker {
    this.title = title
    handle {
        shoppingListRepo.shareShoppingList(
            DbSharedShoppingListRequest(
                shoppingList.id,
                consumer,
                shoppingListsOfUserConsumer
            )
        ).result.let { shoppingList = shoppingList.copy(purchaseList = it.purchaseList) }
    }
}