package com.shopping_list.be.lib.telegram.core.models

import kotlinx.serialization.Serializable

@Serializable
data class CallbackQuery(
    val id: String,
    val from: User,
    val data: String
)
