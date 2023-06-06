package com.shopping_list.backend.shopping_list.logic.workers

import com.shopping_list.common.context.BeContext
import ru.fit_changes.cor.CorChainDsl
import ru.fit_changes.cor.worker

fun CorChainDsl<BeContext>.checkDuplicates(title: String) = worker {
    this.title = title
    handle {
//        val check = shoppingList.purchaseList.map { it.name }.contains(purchaseList)
    }
}