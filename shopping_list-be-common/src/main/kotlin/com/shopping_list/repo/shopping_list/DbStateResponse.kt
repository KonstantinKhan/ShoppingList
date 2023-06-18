package com.shopping_list.repo.shopping_list

import com.shopping_list.common.models.Action
import com.shopping_list.common.models.MessageId
import com.shopping_list.common.models.shopping_list.ShoppingListId
import com.shopping_list.common.models.UserId

class DbStateResponse(
    val userId: UserId = UserId.NONE,
    val shoppingListId: ShoppingListId = ShoppingListId.NONE,
    val messageId: MessageId = MessageId.NONE,
    val action: Action = Action.NONE
)