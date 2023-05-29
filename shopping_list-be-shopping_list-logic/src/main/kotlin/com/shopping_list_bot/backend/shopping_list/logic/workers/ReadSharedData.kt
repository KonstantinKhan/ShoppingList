package com.shopping_list_bot.backend.shopping_list.logic.workers

import com.shopping_list_bot.common.context.BeContextShoppingList
import com.shopping_list_bot.repo.shopping_list.DbShoppingListIdRequest
import ru.fit_changes.cor.CorChainDsl
import ru.fit_changes.cor.worker

fun CorChainDsl<BeContextShoppingList>.readSharedData(title: String) = worker {
    this.title = title
    handle {
        shoppingListRepo.readSharedData(DbShoppingListIdRequest(shoppingList.id, shoppingList.user)).let {
            dependentShoppingLists = it.sharedShoppingLists
        }
        println("shared: $dependentShoppingLists")
    }
}