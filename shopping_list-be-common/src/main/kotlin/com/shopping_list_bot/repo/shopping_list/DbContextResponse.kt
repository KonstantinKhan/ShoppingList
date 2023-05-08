package com.shopping_list_bot.repo.shopping_list

import com.shopping_list_bot.common.models.MessageId
import com.shopping_list_bot.common.models.ShoppingListId
import com.shopping_list_bot.common.models.UserId

class DbContextResponse(
    val userId: UserId,
    val shoppingListId: ShoppingListId,
    val messageId: MessageId
)