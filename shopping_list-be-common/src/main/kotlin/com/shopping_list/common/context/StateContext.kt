package com.shopping_list.common.context

import com.shopping_list.common.models.MessageId
import com.shopping_list.common.models.shopping_list.ShoppingListId
import com.shopping_list.common.models.UserId

data class StateContext(
    val userId: UserId,
    val shoppingListId: ShoppingListId,
    val messageId: MessageId
)
