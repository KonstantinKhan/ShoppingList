package com.shopping_list.backend.shopping_list.logic.workers

import com.shopping_list.common.context.BeContext
import com.shopping_list.repo.shopping_list.DBMessageIdRequest
import ru.fit_changes.cor.CorChainDsl
import ru.fit_changes.cor.worker

fun CorChainDsl<BeContext>.repoReadMessageId(title: String) = worker {
    this.title = title
    handle {
        shoppingListRepo.readMessageId(DBMessageIdRequest(shoppingList.user.userId)).result?.let {
            messageId = it
        }
    }
}