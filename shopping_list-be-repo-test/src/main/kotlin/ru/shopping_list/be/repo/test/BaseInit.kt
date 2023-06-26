package ru.shopping_list.be.repo.test

import com.shopping_list.common.models.shopping_list.ShoppingListId
import com.shopping_list.common.models.shopping_list.ShoppingListModel
import com.shopping_list.common.models.TgUser
import com.shopping_list.common.models.UserId
import com.shopping_list.common.models.shopping_list.ShoppingListTitle

abstract class BaseInit : IInitObjects<ShoppingListModel> {
    fun createInitShoppingLists(): List<ShoppingListModel> = initObjects
}