package ru.ktglib.transport.models

import io.kotest.assertions.json.shouldEqualJson
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe
import ru.ktglib.transport.models.Chat.Companion.CHAT_FROM_FULL_JSON
import ru.ktglib.transport.models.Chat.Companion.CHAT_FROM_PART_JSON
import ru.ktglib.transport.models.Chat.Companion.CHAT_FROM_REDUNDANT_PROPERTIES_JSON
import ru.ktglib.transport.models.Chat.Companion.CHAT_FULL_JSON
import ru.ktglib.transport.models.Chat.Companion.FULL_CHAT
import ru.ktglib.transport.models.Chat.Companion.PART_CHAT

class ChatSerializationTest : ShouldSpec({
    should("serialize the chat model") {
        FULL_CHAT.toJson().toString() shouldEqualJson CHAT_FULL_JSON.toString()
    }
    should("deserialize chat model from full json") {
        CHAT_FROM_FULL_JSON shouldBe FULL_CHAT
    }
    should("deserialize chat model from part json") {
        CHAT_FROM_PART_JSON shouldBe PART_CHAT
    }
    should("deserialize chat model from redundant properties json") {
        CHAT_FROM_REDUNDANT_PROPERTIES_JSON shouldBe PART_CHAT
    }
})