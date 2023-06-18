package com.shopping_list.backend.shopping_list.logic.workers

import com.shopping_list.common.context.BeContext
import com.shopping_list.common.models.MessageId
import com.shopping_list.common.models.shopping_list.ShoppingListId
import com.shopping_list.repo.shopping_list.DbStateRequest
import com.shopping_list.repo.shopping_list.DbUserIdRequest
import ru.fit_changes.cor.CorChainDsl
import ru.fit_changes.cor.worker
import ru.ktglib.types.Message

fun CorChainDsl<BeContext>.sendSharedShoppingList(title: String) = worker {
    this.title = title
    on { errors.isEmpty() }
    handle {
        println("handle")
        httpClient.sendRecipientNotification(this)

        httpClient.sendCurrentShoppingList(this.copy(shoppingList = shoppingList.copy(user = recipient))).result?.let {
            if (it is Message) {
                shoppingListRepo.readState(DbUserIdRequest(recipient.userId)).let { response ->
                    if (response.shoppingListId == ShoppingListId.NONE && response.messageId == MessageId.NONE) {
                        shoppingListRepo.createState(
                            DbStateRequest(
                                userId = recipient.userId,
                                shoppingListId = duplicateShoppingList.id,
                                messageId = MessageId(id = it.messageId)
                            )
                        )
                    } else {
                        shoppingListRepo.updateStateContext(
                            DbStateRequest(
                                recipient.userId,
                                shoppingListId = duplicateShoppingList.id,
                                messageId = MessageId(it.messageId)
                            )
                        )
                    }
                }
            }
        }
    }
}