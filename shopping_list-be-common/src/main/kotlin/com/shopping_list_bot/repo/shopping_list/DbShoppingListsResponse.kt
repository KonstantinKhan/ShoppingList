package com.shopping_list_bot.repo.shopping_list

import com.shopping_list_bot.common.models.shopping_list.ShoppingListId

class DbShoppingListsResponse(
    val shoppingLists: Collection<ShoppingListId>
)