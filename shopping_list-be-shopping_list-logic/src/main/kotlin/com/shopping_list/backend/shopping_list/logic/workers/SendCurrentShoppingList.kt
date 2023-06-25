package com.shopping_list.backend.shopping_list.logic.workers

import com.shopping_list.common.context.BeContext
import com.shopping_list.common.models.Action
import com.shopping_list.common.models.MessageId
import com.shopping_list.common.models.TgUser
import com.shopping_list.repo.shopping_list.DbShoppingListIdRequest
import com.shopping_list.repo.shopping_list.DbStateRequest
import ru.fit_changes.cor.CorChainDsl
import ru.fit_changes.cor.worker
import ru.ktglib.types.Message

fun CorChainDsl<BeContext>.sendCurrentShoppingList(title: String) = worker {
    this.title = title
    on { errors.isEmpty() }
    handle {
        if (recipient == TgUser.NONE)
            shoppingListRepo.readSharedData(DbShoppingListIdRequest(shoppingList.id))
                .sharedShoppingLists.takeIf { it.isNotEmpty() }?.let { lists ->
                    lists.forEach { list ->
//                        httpClient.sendCurrentShoppingList(
//                            this.copy(
//                                shoppingList = shoppingList.copy(
//                                    user = list.user
//                                )
//                            )
//                        ).result?.let {
//                            shoppingListRepo.updateState(
//                                DbStateRequest(
//                                    userId = list.user.userId,
//                                    shoppingListId = list.id,
//                                    messageId = MessageId((it as Message).messageId),
//                                    action = Action.UPDATE_PURCHASE_LIST
//                                )
//                            )
//                        }
                    }
                }
        httpClient.sendCurrentShoppingList(this).result?.let {
            messageId = MessageId((it as Message).messageId)
        }
    }
    except {
        println("error: $it")
    }
}