package com.shopping_list_bot.repo.shopping_list

import com.shopping_list_bot.common.models.ShoppingListId
import com.shopping_list_bot.common.models.TgUser

class DbShoppingListIdRequest(
    val shoppingListId: ShoppingListId,
    val user: TgUser
)