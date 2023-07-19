package com.shopping_list.backend.shopping_list.logic.workers

import com.shopping_list.common.context.BeContext
import com.shopping_list.common.models.Action
import ru.fit_changes.cor.CorChainDsl
import ru.fit_changes.cor.worker

fun CorChainDsl<BeContext>.showListsForDelete() = worker {
    on {
        action == Action.CHOOSE_LIST
                || action == Action.DETACH_LIST
                || action == Action.DELETE_LIST
                || action == Action.UPDATE_LIST
    }
    handle {
        sender.showListsForDelete(this).result.let {
            messageId = it.messageId
        }
    }
}