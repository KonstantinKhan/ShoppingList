package com.shopping_list.backend.shopping_list.logic.workers

import com.shopping_list.common.context.BeContext
import com.shopping_list.common.models.Action
import com.shopping_list.common.models.MessageId
import com.shopping_list.common.models.shopping_list.ShoppingListId
import com.shopping_list.repo.shopping_list.DbStateRequest
import com.shopping_list.repo.shopping_list.DbUserIdRequest
import ru.fit_changes.cor.CorChainDsl
import ru.fit_changes.cor.worker

fun CorChainDsl<BeContext>.sendSharedShoppingList(title: String) = worker {
    this.title = title
    on { errors.isEmpty() }
    handle {
        sender.sendRecipientNotification(this)

        sender.sendCurrentShoppingList(this.copy(shoppingList = shoppingList.copy(user = recipient))).result.let {
            shoppingListRepo.readState(DbUserIdRequest(recipient.userId)).let { response ->
                if (response.shoppingListId == ShoppingListId.NONE && response.messageId == MessageId.NONE) {
                    shoppingListRepo.createState(
                        DbStateRequest(
                            userId = recipient.userId,
                            shoppingListId = duplicateShoppingList.id,
                            messageId = it.messageId,
                            action = Action.UPDATE_PURCHASE_LIST
                        )
                    )
                } else {
                    shoppingListRepo.updateState(
                        DbStateRequest(
                            recipient.userId,
                            shoppingListId = duplicateShoppingList.id,
                            messageId = it.messageId,
                            action = Action.UPDATE_PURCHASE_LIST
                        )
                    )
                }
            }
        }
    }
}