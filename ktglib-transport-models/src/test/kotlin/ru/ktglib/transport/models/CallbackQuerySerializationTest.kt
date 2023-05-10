package ru.ktglib.transport.models

import io.kotest.assertions.json.shouldEqualJson
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe
import ru.ktglib.transport.models.CallbackQuery.Companion.CALLBACK_QUERY_FROM_FULL_JSON
import ru.ktglib.transport.models.CallbackQuery.Companion.CALLBACK_QUERY_FROM_PART_JSON
import ru.ktglib.transport.models.CallbackQuery.Companion.CALLBACK_QUERY_FROM_REDUNDANT_PROPERTIES_JSON
import ru.ktglib.transport.models.CallbackQuery.Companion.CALLBACK_QUERY_FULL_JSON
import ru.ktglib.transport.models.CallbackQuery.Companion.FULL_CALLBACK_QUERY
import ru.ktglib.transport.models.CallbackQuery.Companion.PART_CALLBACK_QUERY

class CallbackQuerySerializationTest: ShouldSpec({
    should("serialize the callback query model") {
        FULL_CALLBACK_QUERY.toJson().toString() shouldEqualJson CALLBACK_QUERY_FULL_JSON.toString()
    }
    should("deserialize callback query from full json") {
        CALLBACK_QUERY_FROM_FULL_JSON shouldBe FULL_CALLBACK_QUERY
    }
    should("deserialize callback query from part json") {
        CALLBACK_QUERY_FROM_PART_JSON shouldBe PART_CALLBACK_QUERY
    }
    should("deserialize callback query from redundant properties json") {
        CALLBACK_QUERY_FROM_REDUNDANT_PROPERTIES_JSON shouldBe PART_CALLBACK_QUERY
    }
})