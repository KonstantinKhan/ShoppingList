package com.shopping_list_bot.common.models

import java.util.UUID

@JvmInline
value class ShoppingListId(private val id: String) {

    constructor(id: UUID) : this(id.toString())

    fun asUUID(): UUID = UUID.fromString(id)

    companion object {
        val NONE = ShoppingListId("")
    }
}