package com.shopping_list_bot.common.context

import com.shopping_list_bot.common.models.MessageId
import com.shopping_list_bot.common.models.shopping_list.ShoppingListId
import com.shopping_list_bot.common.models.UserId

data class StateContext(
    val userId: UserId,
    val shoppingListId: ShoppingListId,
    val messageId: MessageId
)
