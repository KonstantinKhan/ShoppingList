package com.shopping_list.backend.shopping_list.logic.workers

import com.shopping_list.common.context.BeContext
import ru.fit_changes.cor.CorChainDsl
import ru.fit_changes.cor.worker
import ru.ktglib.types.User

fun CorChainDsl<BeContext>.getBotInfo(title: String) = worker {
    on { errors.isNotEmpty() }
    handle {
        httpClient.getMe().result?.let {
            bot = it as User
        }
        println("context: ${this.bot}")
    }
}