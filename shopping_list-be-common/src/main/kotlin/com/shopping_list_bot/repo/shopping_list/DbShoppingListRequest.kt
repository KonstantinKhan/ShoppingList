package com.shopping_list_bot.repo.shopping_list

import com.shopping_list_bot.common.models.ShoppingListModel
import com.shopping_list_bot.repo.IDbRequest

class DbShoppingListRequest(
    val shoppingList: ShoppingListModel,
): IDbRequest