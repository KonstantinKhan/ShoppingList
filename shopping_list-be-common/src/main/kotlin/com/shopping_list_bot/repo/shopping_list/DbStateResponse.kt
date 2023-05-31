package com.shopping_list_bot.repo.shopping_list

import com.shopping_list_bot.common.models.MessageId
import com.shopping_list_bot.common.models.shopping_list.ShoppingListId
import com.shopping_list_bot.common.models.UserId

class DbStateResponse(
    val userId: UserId = UserId.NONE,
    val shoppingListId: ShoppingListId = ShoppingListId.NONE,
    val messageId: MessageId = MessageId.NONE
)