package ru.shopping_list.be.repo.test

import com.shopping_list.common.models.shopping_list.ShoppingListId
import com.shopping_list.common.models.shopping_list.ShoppingListModel
import com.shopping_list.common.models.TgUser
import com.shopping_list.common.models.UserId
import com.shopping_list.common.models.shopping_list.ShoppingListTitle

abstract class BaseInit : IInitObjects<ShoppingListModel> {
    fun createInitShoppingLists(): List<ShoppingListModel> = listOf(
        ShoppingListModel(
            id = ShoppingListId("72086f29-7915-49db-819f-53507bbc0f8b"),
            title = ShoppingListTitle("my list"),
            user = TgUser(UserId(123456), "first", "last", "user"),
            purchaseList = listOf()
        )
    )
}