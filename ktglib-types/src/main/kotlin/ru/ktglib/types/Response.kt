package ru.ktglib.types

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable(ResponseSerializer::class)
interface Response {
    @SerialName("ok")
    val status: Boolean
    val result: Result?
    @SerialName("error_code")
    val errorCode: Int?
    val description: String?

//    @SerialName("error_code")
//    val errorCode: Int? = null
//    val description: String? = null
}
