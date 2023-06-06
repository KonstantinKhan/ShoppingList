package com.shopping_list.common.context

import com.shopping_list.repo.shopping_list.IRepoShoppingList

data class ShoppingListContextConfig(
    val repoShoppingListInMemory: IRepoShoppingList = IRepoShoppingList.NONE,
    val reposShoppingListTest: IRepoShoppingList = IRepoShoppingList.NONE
)
