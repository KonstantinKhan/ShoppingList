package com.shopping_list_bot.common.models

@JvmInline
value class UserId(private val id: String) {

    fun toInt() = id.toInt()

    constructor(id: Int) : this(id.toString())

    companion object {
        val NONE = UserId("")
    }
}