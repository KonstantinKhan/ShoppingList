package com.shopping_list_bot.repo.shopping_list

import com.shopping_list_bot.common.models.`shopping-list`.ShoppingListId

class DbSharedShoppingList(
    val sharedShoppingLists: Collection<ShoppingListId>
)