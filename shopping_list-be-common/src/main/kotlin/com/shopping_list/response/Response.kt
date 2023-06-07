package com.shopping_list.response

import kotlinx.serialization.Serializable
import ru.ktglib.types.Result

@Serializable(ResponseSerializer::class)
interface Response {
    val status: Boolean
    val result: Result?
    val errorCode: Int?
    val description: String?
//
//    @SerialName("error_code")
//    val errorCode: Int? = null
//    val description: String? = null
}
