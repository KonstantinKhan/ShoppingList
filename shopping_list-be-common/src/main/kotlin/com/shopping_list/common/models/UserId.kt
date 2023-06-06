package com.shopping_list.common.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@JvmInline
@Serializable
value class UserId(
    @SerialName("chat_id")
    private val id: String
) {

    fun toInt() = id.toInt()

    constructor(id: Int) : this(id.toString())

    companion object {
        val NONE = UserId("")
    }
}