package com.shopping_list_bot.repo.shopping_list

import com.shopping_list_bot.common.models.ShoppingListId
import com.shopping_list_bot.common.models.TgUser

class DbSharedShoppingListRequest(
    val sourceShoppingList: ShoppingListId,
    val userConsumer: TgUser,
    val shoppingListsOfUserConsumer: Collection<ShoppingListId>
)