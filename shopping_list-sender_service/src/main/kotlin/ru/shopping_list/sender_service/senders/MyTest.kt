package ru.shopping_list.sender_service.senders

import com.shopping_list.response.Response
import kotlinx.serialization.decodeFromString
import ru.ktglib.types.Result
import ru.shopping_list.sender_service.jsonHelper

fun main() {
    println(
        jsonHelper()
            .decodeFromString<Response>(
                """{
                "ok":true,"result":{"id":6237994945,"is_bot":true,"first_name":"khan_test_bot","username":"KhanDevTestBot","can_join_groups":true,"can_read_all_group_messages":false,"supports_inline_queries":false}
            }""".trimIndent().trim()
            )
    )
}