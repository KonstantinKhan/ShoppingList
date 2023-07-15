package com.shopping_list.backend.shopping_list.logic.workers

import com.shopping_list.common.IError
import com.shopping_list.common.context.BeContext
import ru.fit_changes.cor.CorChainDsl
import ru.fit_changes.cor.worker

fun CorChainDsl<BeContext>.checkChat(title: String) = worker {
    this.title = title
    handle {
        val result = sender.getChat(this)
        if (result.error != IError.NONE)
            errors.add(result.error)
    }
}