package ru.ktglib.transport.models

import io.kotest.assertions.json.shouldEqualJson
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe
import ru.ktglib.transport.models.message.Message.Companion.FULL_MESSAGE
import ru.ktglib.transport.models.message.Message.Companion.MESSAGE_FROM_FULL_JSON
import ru.ktglib.transport.models.message.Message.Companion.MESSAGE_FROM_PART_JSON
import ru.ktglib.transport.models.message.Message.Companion.MESSAGE_FROM_REDUNDANT_PROPERTIES_JSON
import ru.ktglib.transport.models.message.Message.Companion.MESSAGE_FULL_JSON
import ru.ktglib.transport.models.message.Message.Companion.PART_MESSAGE

class MessageSerializationTest : ShouldSpec({
    should("serialize the message model") {
        FULL_MESSAGE.toJson().toString() shouldEqualJson MESSAGE_FULL_JSON.toString()
    }
    should("deserialize message model from full json") {
        MESSAGE_FROM_FULL_JSON shouldBe FULL_MESSAGE
    }
    should("deserialize message model from part json") {
        MESSAGE_FROM_PART_JSON shouldBe PART_MESSAGE
    }
    should("deserialize message model from redundant properties json") {
        MESSAGE_FROM_REDUNDANT_PROPERTIES_JSON shouldBe PART_MESSAGE
    }
})