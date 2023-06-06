package com.shopping_list.repo.shopping_list

import com.shopping_list.common.models.shopping_list.PurchaseModel
import com.shopping_list.common.models.shopping_list.ShoppingListId
import com.shopping_list.repo.IDbRequest

class DbPurchaseModelRequest(
    val shoppingListId: ShoppingListId,
    val purchase: Collection<PurchaseModel>
) : IDbRequest