package com.shopping_list.backend.shopping_list.logic.workers

import com.shopping_list.common.context.BeContext
import com.shopping_list.common.models.Action
import ru.fit_changes.cor.CorChainDsl
import ru.fit_changes.cor.worker

fun CorChainDsl<BeContext>.showLists() = worker {
    on {
        action == Action.CHOOSE_LIST
    }
    handle {
        httpClient.showLists(this)
    }
}