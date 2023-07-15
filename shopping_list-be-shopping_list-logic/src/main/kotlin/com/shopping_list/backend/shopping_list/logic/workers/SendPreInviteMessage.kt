package com.shopping_list.backend.shopping_list.logic.workers

import com.shopping_list.common.context.BeContext
import com.shopping_list.common.models.CommonErrorModel
import ru.fit_changes.cor.CorChainDsl
import ru.fit_changes.cor.worker

fun CorChainDsl<BeContext>.sendPreInviteMessage(title: String) = worker {
    this.title = title
    on { errors.isNotEmpty() && !errors.contains(CommonErrorModel("ShoppingList is empty")) }
    handle {
        sender.sendPreInviteMessage(this)
    }
}