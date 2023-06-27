package ru.shopping_list.be.repo.test

import com.shopping_list.common.models.shopping_list.ShoppingListModel

abstract class BaseInit : IInitObjects {
    fun createInitShoppingLists(): List<ShoppingListModel> = initShoppingListModels
}