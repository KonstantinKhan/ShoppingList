package com.shopping_list.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseWithMessage(
    @SerialName("ok")
    override val status: Boolean,
    override val result: Result,
    override val errorCode: Int? = null,
    override val description: String? = null,
) : Response