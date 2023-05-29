package com.shopping_list_bot.backend.shopping_list.logic.workers

import com.shopping_list_bot.common.context.BeContextShoppingList
import ru.fit_changes.cor.CorChainDsl
import ru.fit_changes.cor.worker

fun CorChainDsl<BeContextShoppingList>.checkDuplicates(title: String) = worker {
    this.title = title
    handle {
//        val check = shoppingList.purchaseList.map { it.name }.contains(purchaseList)
    }
}