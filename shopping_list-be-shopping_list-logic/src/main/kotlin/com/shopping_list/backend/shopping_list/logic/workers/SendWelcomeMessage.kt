package com.shopping_list.backend.shopping_list.logic.workers

import com.shopping_list.common.context.BeContext
import com.shopping_list.common.models.Action
import com.shopping_list.common.models.shopping_list.ShoppingListModel
import ru.fit_changes.cor.CorChainDsl
import ru.fit_changes.cor.worker

fun CorChainDsl<BeContext>.sendWelcomeMessage(title: String) = worker {
    this.title = title
    on { action == Action.NONE }
    handle { sender.sendWelcomeMessage(this) }
    except { println("error in sendWelcomeMessage: ${it.message}") }
}