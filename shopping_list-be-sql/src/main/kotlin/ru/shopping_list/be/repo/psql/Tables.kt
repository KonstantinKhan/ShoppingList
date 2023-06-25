package ru.shopping_list.be.repo.psql

import com.shopping_list.common.models.TgUser
import com.shopping_list.common.models.UserId
import com.shopping_list.common.models.shopping_list.PurchaseModel
import com.shopping_list.common.models.shopping_list.ShoppingListId
import com.shopping_list.common.models.shopping_list.ShoppingListModel
import com.shopping_list.common.models.shopping_list.ShoppingListTitle
import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table

object ShoppingListTable : Table("shopping_list") {
    val id = uuid("id").autoGenerate().uniqueIndex()
    val userId = reference("user_id", TgUsersTable.id, onDelete = ReferenceOption.CASCADE)
    val title = varchar("title", 128)

    override val primaryKey = PrimaryKey(id)

    fun from(res: ResultRow) = ShoppingListModel(
        id = ShoppingListId(res[id]),
        title = ShoppingListTitle(res[title]),
        user = TgUser(
            userId = UserId(res[userId]),
            firstName = ""
        )
    )
}

object TgUsersTable : Table("tg_users") {
    val id = long("id")
    val firstName = varchar("first_name", 128)
    val lastName = varchar("last_name", 128)
    val userName = varchar("user_name", 128)

    override val primaryKey = PrimaryKey(id)

    fun from(res: ResultRow) = TgUser(
        userId = UserId(res[id]),
        firstName = res[firstName],
        lastName = res[lastName],
        userName = res[userName]
    )
}

object PurchaseTable : Table("purchase") {
    val shoppingListId = reference("shopping_list_id", ShoppingListTable.id, onDelete = ReferenceOption.CASCADE)
    val name = varchar("name", 128)
    val checked = bool("checked")

    fun from(res: ResultRow) = PurchaseModel(
        name = res[name],
        checked = res[checked]
    )
}

object StateTable : Table("state") {
    val userId = reference("user_id", TgUsersTable.id, onDelete = ReferenceOption.CASCADE).uniqueIndex()
    val shoppingListId = uuid("shopping_list_id")
    val lastMessageId = integer("message_id")
    val action = varchar("action", 128)
}

object SharedShoppingListTable : Table("shared_shopping_list") {
    val sourceShoppingList = reference(
        "source_shopping_list_id", ShoppingListTable.id, onDelete = ReferenceOption.CASCADE
    )
    val duplicateShoppingList = reference(
        "duplicate_shopping_list_id", ShoppingListTable.id, onDelete = ReferenceOption.CASCADE
    )
}