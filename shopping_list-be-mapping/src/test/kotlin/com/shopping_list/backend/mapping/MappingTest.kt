package com.shopping_list.backend.mapping

import com.shopping_list_bot.common.context.BeContextShoppingList
import com.shopping_list_bot.common.models.UserId
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe
import ru.ktglib.transport.models.update.UpdateWithCallbackQuery.Companion.UPDATE_WITH_CALLBACK_QUERY
import ru.ktglib.transport.models.update.UpdateWithMessage.Companion.UPDATE_WITH_MESSAGE

class MappingTest : ShouldSpec({
    val context = BeContextShoppingList()

    should("successfully receive an update with the message") {
        val actual = context.setQuery(UPDATE_WITH_MESSAGE)
        with(actual.shoppingList.user) {
            userId shouldBe UserId(123456)
            firstName shouldBe "first"
            lastName shouldBe "last"
            userName shouldBe "name"
        }
        actual.purchase shouldBe "some text"
    }

    should("successfully receive an update with the callback query") {
        val actual = context.setQuery(UPDATE_WITH_CALLBACK_QUERY)
        with(actual.shoppingList.user) {
            userId shouldBe UserId(123456)
            firstName shouldBe "first"
            lastName shouldBe "last"
            userName shouldBe "name"
        }
        actual.purchase shouldBe "some data"
    }
})