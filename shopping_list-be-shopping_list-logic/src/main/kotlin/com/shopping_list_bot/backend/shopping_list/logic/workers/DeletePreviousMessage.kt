package com.shopping_list_bot.backend.shopping_list.logic.workers

import com.shopping_list_bot.common.context.BeContextShoppingList
import com.shopping_list_bot.common.models.MessageId
import ru.fit_changes.cor.CorChainDsl
import ru.fit_changes.cor.worker

fun CorChainDsl<BeContextShoppingList>.deletePreviousMessage(title: String) = worker {
    this.title = title
    on {
        messageId != MessageId.NONE
    }
    handle {
        httpClient.deleteMessage(this)
    }
}