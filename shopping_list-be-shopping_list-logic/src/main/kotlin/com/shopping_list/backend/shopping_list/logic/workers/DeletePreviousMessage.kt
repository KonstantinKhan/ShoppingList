package com.shopping_list.backend.shopping_list.logic.workers

import com.shopping_list.common.context.BeContext
import com.shopping_list.common.models.MessageId
import ru.fit_changes.cor.CorChainDsl
import ru.fit_changes.cor.worker

fun CorChainDsl<BeContext>.deletePreviousMessage(title: String) = worker {
    this.title = title
    on {
        messageId != MessageId.NONE
    }
    handle {
//        httpClient.deleteMessage(this)
    }
}