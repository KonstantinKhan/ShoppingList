package com.shopping_list.backend.mapping

import com.shopping_list.common.context.BeContext
import com.shopping_list.common.models.UserId
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe
import ru.ktglib.transport.models.update.UpdateWithCallbackQuery.Companion.UPDATE_WITH_CALLBACK_QUERY
import ru.ktglib.transport.models.update.UpdateWithMessage.Companion.UPDATE_WITH_MESSAGE

class MappingTest : ShouldSpec({
    val context = BeContext()

    should("successfully receive an update with the message") {
        val actual = context.setQuery(UPDATE_WITH_MESSAGE)
        with(actual.shoppingList.user) {
            userId shouldBe UserId(123456)
            firstName shouldBe "first"
            lastName shouldBe "last"
            userName shouldBe "name"
        }
        actual.purchaseList shouldBe "some text"
    }

    should("successfully receive an update with the callback query") {
        val actual = context.setQuery(UPDATE_WITH_CALLBACK_QUERY)
        with(actual.shoppingList.user) {
            userId shouldBe UserId(123456)
            firstName shouldBe "first"
            lastName shouldBe "last"
            userName shouldBe "name"
        }
        actual.purchaseList shouldBe "some data"
    }
})