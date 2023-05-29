package com.shopping_list_bot.backend.shopping_list.logic.workers

import com.shopping_list_bot.common.context.BeContextShoppingList
import com.shopping_list_bot.repo.shopping_list.DbUserIdRequest
import ru.fit_changes.cor.CorChainDsl
import ru.fit_changes.cor.worker

fun CorChainDsl<BeContextShoppingList>.readShoppingListsOfUserConsumer(title: String) = worker {
    this.title = title
    handle {
        shoppingListRepo.readShoppingLists(DbUserIdRequest(consumer.userId)).shoppingLists.let {
            shoppingListsOfUserConsumer.addAll(it)
        }
    }
}