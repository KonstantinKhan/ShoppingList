package com.shopping_list.backend.shopping_list.logic.workers

import com.shopping_list.common.context.BeContext
import com.shopping_list.repo.shopping_list.DbUserIdRequest
import ru.fit_changes.cor.CorChainDsl
import ru.fit_changes.cor.worker

fun CorChainDsl<BeContext>.readShoppingListsOfRecipient(title: String) = worker {
    this.title = title
    on { errors.isEmpty() }
    handle {
        shoppingListRepo.readShoppingLists(DbUserIdRequest(recipient.userId)).shoppingLists.let {
            shoppingListsOfRecipient.addAll(it)
        }
        println("lists: $shoppingListsOfRecipient")
    }
}