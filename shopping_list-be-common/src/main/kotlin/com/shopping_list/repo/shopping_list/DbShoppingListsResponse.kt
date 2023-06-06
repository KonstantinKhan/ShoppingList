package com.shopping_list.repo.shopping_list

import com.shopping_list.common.models.shopping_list.ShoppingListId

class DbShoppingListsResponse(
    val shoppingLists: Collection<ShoppingListId>
)