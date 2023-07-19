package com.shopping_list.backend.shopping_list.logic.workers

import com.shopping_list.common.context.BeContext
import com.shopping_list.common.models.CommonErrorModel
import com.shopping_list.common.models.shopping_list.ShoppingListId
import com.shopping_list.repo.shopping_list.DbShoppingListIdRequest
import ru.fit_changes.cor.CorChainDsl
import ru.fit_changes.cor.worker

fun CorChainDsl<BeContext>.repoReadShoppingList(title: String) = worker {
    this.title = title
    on { shoppingList.id != ShoppingListId.NONE }
    handle {
        val result = shoppingListRepo.readShoppingList(DbShoppingListIdRequest(shoppingList.id))
        result.result.let {
            dbShoppingList = it
        }
        result.error?.let { errors.add(it) }
    }
    except {
        errors.add(CommonErrorModel(it.message ?: ""))
    }
}