package com.shopping_list.repo.shopping_list

import com.shopping_list.common.models.Action
import com.shopping_list.common.models.MessageId
import com.shopping_list.common.models.shopping_list.ShoppingListId
import com.shopping_list.common.models.UserId
import com.shopping_list.repo.IDbRequest

class DbStateRequest(
    val userId: UserId = UserId.NONE,
    val shoppingListId: ShoppingListId,
    val messageId: MessageId = MessageId(-1),
    val action: Action = Action.NONE
) : IDbRequest