package com.shopping_list.backend.shopping_list.logic.workers

import com.shopping_list.common.context.BeContext
import com.shopping_list.repo.shopping_list.DbUserIdRequest
import ru.fit_changes.cor.CorChainDsl
import ru.fit_changes.cor.worker

fun CorChainDsl<BeContext>.repoReadState(title: String) = worker {
    this.title = title
    handle {
        shoppingListRepo.readState(DbUserIdRequest(shoppingList.user.userId)).let {
            messageId = it.messageId
            shoppingList = shoppingList.copy(it.shoppingListId)
            action = it.action
        }
        println("action__: $action")
    }
}