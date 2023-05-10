package ru.ktglib.transport.models

import io.kotest.assertions.json.shouldEqualJson
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe
import ru.ktglib.transport.models.update.UpdateWithCallbackQuery.Companion.UPDATE_WITH_CALLBACK_QUERY
import ru.ktglib.transport.models.update.UpdateWithCallbackQuery.Companion.UPDATE_WITH_CALLBACK_QUERY_FROM_JSON
import ru.ktglib.transport.models.update.UpdateWithCallbackQuery.Companion.UPDATE_WITH_CALLBACK_QUERY_FROM_REDUNDANT_PROPERTIES_JSON
import ru.ktglib.transport.models.update.UpdateWithCallbackQuery.Companion.UPDATE_WITH_CALLBACK_QUERY_JSON
import ru.ktglib.transport.models.update.UpdateWithMessage.Companion.UPDATE_WITH_MESSAGE
import ru.ktglib.transport.models.update.UpdateWithMessage.Companion.UPDATE_WITH_MESSAGE_FROM_JSON
import ru.ktglib.transport.models.update.UpdateWithMessage.Companion.UPDATE_WITH_MESSAGE_FROM_REDUNDANT_PROPERTIES_JSON
import ru.ktglib.transport.models.update.UpdateWithMessage.Companion.UPDATE_WITH_MESSAGE_JSON

class UpdateSerializationTest : ShouldSpec({
    // UpdateWithMessage
    should("serialize update with message model") {
        UPDATE_WITH_MESSAGE.toJson().toString() shouldEqualJson UPDATE_WITH_MESSAGE_JSON.toString()
    }
    should("deserialize update with message model from json") {
        UPDATE_WITH_MESSAGE_FROM_JSON shouldBe UPDATE_WITH_MESSAGE
    }
    should("deserialize update with message model from redundant properties json") {
        UPDATE_WITH_MESSAGE_FROM_REDUNDANT_PROPERTIES_JSON shouldBe UPDATE_WITH_MESSAGE
    }

    // UpdateWithCallbackQuery
    should("serialize update with callback query model") {
        UPDATE_WITH_CALLBACK_QUERY.toJson().toString() shouldEqualJson UPDATE_WITH_CALLBACK_QUERY_JSON.toString()
    }
    should("deserialize update with callback query model from json") {
        UPDATE_WITH_CALLBACK_QUERY_FROM_JSON shouldBe UPDATE_WITH_CALLBACK_QUERY
    }
    should("deserialize update with callback query model from redundant properties json") {
        UPDATE_WITH_CALLBACK_QUERY_FROM_REDUNDANT_PROPERTIES_JSON shouldBe UPDATE_WITH_CALLBACK_QUERY
    }
})