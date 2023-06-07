package com.shopping_list.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.ktglib.types.Result

@Serializable
data class ResponseWithError(
    @SerialName("ok")
    override val status: Boolean,
    override val result: Result? = null,
    @SerialName("error_code")
    override val errorCode: Int,
    override val description: String
) : Response
