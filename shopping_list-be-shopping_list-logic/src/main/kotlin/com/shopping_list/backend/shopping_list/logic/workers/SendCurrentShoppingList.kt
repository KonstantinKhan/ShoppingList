package com.shopping_list.backend.shopping_list.logic.workers

import com.shopping_list.common.context.BeContext
import com.shopping_list.common.models.MessageId
import ru.fit_changes.cor.CorChainDsl
import ru.fit_changes.cor.worker
import ru.ktglib.types.Message

fun CorChainDsl<BeContext>.sendCurrentShoppingList(title: String) = worker {
    this.title = title
    on { errors.isEmpty() }
    handle {
        httpClient.sendCurrentShoppingList(this).result?.let {
            messageId = MessageId((it as Message).messageId)
        }
    }
}