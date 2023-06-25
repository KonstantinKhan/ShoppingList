package com.shopping_list.backend.shopping_list.logic.workers

import com.shopping_list.common.context.BeContext
import com.shopping_list.repo.shopping_list.DbShoppingListIdRequest
import ru.fit_changes.cor.CorChainDsl
import ru.fit_changes.cor.worker

fun CorChainDsl<BeContext>.detachList() = worker {
    handle {
        shoppingListRepo.deleteShareData(DbShoppingListIdRequest(shoppingList.id))
    }
}