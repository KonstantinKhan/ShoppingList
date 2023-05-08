package com.shopping_list_bot.common.context

import com.shopping_list_bot.repo.shopping_list.IRepoShoppingList

data class ShoppingListContextConfig(
    val repoShoppingListInMemory: IRepoShoppingList = IRepoShoppingList.NONE,
    val reposShoppingListTest: IRepoShoppingList = IRepoShoppingList.NONE
)
