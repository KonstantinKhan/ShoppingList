package com.shopping_list.backend.shopping_list.logic.workers

import com.shopping_list.common.context.BeContext
import com.shopping_list.repo.shopping_list.DbShoppingListRequest
import ru.fit_changes.cor.CorChainDsl
import ru.fit_changes.cor.worker

fun CorChainDsl<BeContext>.repoCreate(title: String) = worker {
    this.title = title
    handle {
        shoppingListRepo = config.repoShoppingListInMemory
        shoppingListRepo.createShoppingList(DbShoppingListRequest(dbShoppingList.copy(user = shoppingList.user)))
    }
}