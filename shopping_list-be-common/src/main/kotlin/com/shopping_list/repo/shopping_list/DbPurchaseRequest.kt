package com.shopping_list.repo.shopping_list

import com.shopping_list.common.models.shopping_list.ShoppingListId
import com.shopping_list.common.models.UserId

class DbPurchaseRequest(
    val purchase: Collection<String>,
    val shoppingListId: ShoppingListId,
    val userId: UserId
)