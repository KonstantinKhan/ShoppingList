package com.shopping_list.backend.shopping_list.logic.workers

import com.shopping_list.common.context.BeContext
import com.shopping_list.repo.shopping_list.DbFilterShoppingListRequest
import ru.fit_changes.cor.CorChainDsl
import ru.fit_changes.cor.worker

fun CorChainDsl<BeContext>.searchLists(title: String) = worker {
    handle {
        shoppingListRepo.searchShoppingList(DbFilterShoppingListRequest(shoppingList.user.userId)).shoppingLists.let {
            shoppingLists.addAll(it)
        }
        httpClient.showLists(this)
    }
    except {
        println("exception: ${it.message}")
    }
}