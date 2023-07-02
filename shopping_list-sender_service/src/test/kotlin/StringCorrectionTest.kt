import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe
import ru.shopping_list.sender_service.helpers.replaceExt

class StringCorrectionTest : ShouldSpec() {
    init {
        should("test") {
            "Что\\-нибудь\\!" shouldBe "Что-нибудь!".replaceExt()
        }
    }
}