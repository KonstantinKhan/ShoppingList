package ru.ktglib.transport.models.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AnswerCallbackQueryRequest(
    @SerialName("callback_query_id")
    val callbackQueryId: String,
    val text: String? = null,
    @SerialName("show_alert")
    val showAlert: Boolean? = null,
    val url: String? = null,
    @SerialName("cache_time")
    val cacheTime: Int? = null
)