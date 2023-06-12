package com.shopping_list.repo.shopping_list

import com.shopping_list.common.models.MessageId
import com.shopping_list.common.models.UserId
import com.shopping_list.common.models.shopping_list.ShoppingListModel

class DbSharedShoppingList(
    val sharedShoppingLists: Collection<ShoppingListModel>
)