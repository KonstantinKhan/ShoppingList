package com.shopping_list.repo.shopping_list

import com.shopping_list.common.models.shopping_list.ShoppingListModel
import com.shopping_list.repo.IDbRequest

class DbShoppingListRequest(
    val shoppingList: ShoppingListModel,
): IDbRequest