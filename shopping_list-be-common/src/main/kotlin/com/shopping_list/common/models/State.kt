package com.shopping_list.common.models

import com.shopping_list.common.models.shopping_list.ShoppingListId

data class State(
    val userId: UserId,
    val messageId: MessageId,
    val shoppingListId: ShoppingListId = ShoppingListId.NONE,
    val action: Action = Action.NONE
)
