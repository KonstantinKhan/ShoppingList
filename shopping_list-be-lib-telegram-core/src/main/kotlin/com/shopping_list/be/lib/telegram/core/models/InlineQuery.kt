package com.shopping_list.be.lib.telegram.core.models

import kotlinx.serialization.Serializable

@Serializable
data class InlineQuery(
    val id: String,
    val from: User,
    val query: String,
    val offset: String
)
