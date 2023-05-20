package ru.ktglib.transport.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserShared(
    @SerialName("request_id")
    val requestId: Int,
    @SerialName("user_id")
    val userId: Int
) {
    companion object {
        val NONE = UserShared(1, -1)
    }
}
