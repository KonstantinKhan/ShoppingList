package com.shopping_list_bot.repo.shopping_list

import com.shopping_list_bot.common.models.ShoppingListId
import com.shopping_list_bot.common.models.UserId

class DbPurchaseRequest(
    val purchase: String,
    val shoppingListId: ShoppingListId,
    val userId: UserId
)