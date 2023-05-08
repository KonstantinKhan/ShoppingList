package com.shopping_list_bot.repo.shopping_list

import com.shopping_list_bot.common.models.MessageId
import com.shopping_list_bot.common.models.ShoppingListId
import com.shopping_list_bot.common.models.UserId
import com.shopping_list_bot.repo.IDbRequest

class DbStateContextRequest(
    val userId: UserId,
    val messageId: MessageId,
    val shoppingListId: ShoppingListId
): IDbRequest