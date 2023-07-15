package com.shopping_list.backend.shopping_list.logic.workers

import com.shopping_list.common.context.BeContext
import ru.fit_changes.cor.CorChainDsl
import ru.fit_changes.cor.worker

fun CorChainDsl<BeContext>.getBotInfo(title: String) = worker {
    on { errors.isNotEmpty() }
    handle {
        sender.getMe().user.let {
            bot = it
        }
    }
    except {
        println("Error: ${it.message}")
    }
}