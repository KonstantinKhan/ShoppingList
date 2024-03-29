package ru.ktglib.types

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.ktglib.types.Result

@Serializable
data class ResponseWithMessage(
    @SerialName("ok")
    override val status: Boolean,
    override val result: Result,
    @SerialName("error_code")
    override val errorCode: Int? = null,
    override val description: String? = null,
) : Response