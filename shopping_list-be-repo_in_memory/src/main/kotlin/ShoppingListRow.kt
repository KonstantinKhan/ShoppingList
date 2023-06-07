import com.shopping_list.common.models.shopping_list.PurchaseModel
import com.shopping_list.common.models.shopping_list.ShoppingListModel
import com.shopping_list.common.models.TgUser
import com.shopping_list.common.models.UserId
import java.io.Serializable

data class ShoppingListRow(
    val userId: Long,
    val shoppingList: ArrayList<PurchaseModel>,
) : Serializable {
    constructor(internal: ShoppingListModel) : this(
        userId = internal.user.userId.toLong(),
        shoppingList = ArrayList(internal.purchaseList),
    )

    fun toInternal(): ShoppingListModel = ShoppingListModel(
        user = TgUser(UserId(userId), "user"),
        purchaseList = shoppingList,
    )

}