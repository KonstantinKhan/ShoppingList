package ru.ktglib.transport.models

import io.kotest.assertions.json.*
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe
import ru.ktglib.types.User.Companion.FULL_USER
import ru.ktglib.types.User.Companion.PART_USER
import ru.ktglib.types.User.Companion.USER_FROM_FULL_JSON
import ru.ktglib.types.User.Companion.USER_FROM_PART_JSON
import ru.ktglib.types.User.Companion.USER_FROM_REDUNDANT_PROPERTIES_JSON
import ru.ktglib.types.User.Companion.USER_FULL_JSON

class UserSerializationTest : ShouldSpec({
    should("serialize the user model") {
        FULL_USER.toJson().toString() shouldEqualJson USER_FULL_JSON.toString()
    }
    should("deserialize user model from full json") {
        USER_FROM_FULL_JSON shouldBe FULL_USER
    }
    should("deserialize user model from part json") {
        USER_FROM_PART_JSON shouldBe PART_USER
    }
    should("deserialize user model from redundant properties json") {
        USER_FROM_REDUNDANT_PROPERTIES_JSON shouldBe PART_USER
    }
})