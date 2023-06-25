package com.shopping_list.backend.shopping_list.logic.workers

import com.shopping_list.common.context.BeContext
import com.shopping_list.repo.shopping_list.DbShoppingListIdRequest
import ru.fit_changes.cor.CorChainDsl
import ru.fit_changes.cor.worker

fun CorChainDsl<BeContext>.repoReadShoppingList(title: String) = worker {
    this.title = title
    handle {
        shoppingListRepo.readShoppingList(DbShoppingListIdRequest(shoppingList.id)).result.let {
            dbShoppingList = it
            println("dbShoppingList: $dbShoppingList")
        }
    }
}