package com.shopping_list_bot.common.models.`shopping-list`

@JvmInline
value class ShoppingListTitle(private val title: String) {
    companion object {
        val NONE = ShoppingListTitle("")
    }
}