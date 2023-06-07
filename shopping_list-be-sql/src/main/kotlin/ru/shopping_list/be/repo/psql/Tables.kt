package ru.shopping_list.be.repo.psql

import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.Table

object ShoppingListTable : Table("shopping_list") {
    val id = uuid("id").autoGenerate().uniqueIndex()
    val userId = reference("user_id", TgUsersTable.id, onDelete = ReferenceOption.CASCADE)
    val title = varchar("title", 128)

    override val primaryKey = PrimaryKey(id)
}

object TgUsersTable : Table("tg_users") {
    val id = long("id")
    val firstName = varchar("first_name", 128)
    val lastName = varchar("last_name", 128)
    val userName = varchar("user_name", 128)

    override val primaryKey = PrimaryKey(id)
}

object PurchaseTable : Table("purchase") {
    val shoppingListId = reference("shopping_list_id", ShoppingListTable.id, onDelete = ReferenceOption.CASCADE)
    val name = varchar("name", 128)
    val checked = bool("checked")
}

object StateTable : Table("state") {
    val userId = reference("user_id", TgUsersTable.id, onDelete = ReferenceOption.CASCADE)
    val shoppingListId = reference("shopping_list_id", ShoppingListTable.id, onDelete = ReferenceOption.CASCADE)
    val lastMessageId = integer("message_id")
}

object SharedShoppingListTable : Table("shared_shopping_list") {
    val sourceShoppingList = reference(
        "source_shopping_list_id", ShoppingListTable.id, onDelete = ReferenceOption.CASCADE
    )
    val duplicateShoppingList = reference(
        "duplicate_shopping_list_id", ShoppingListTable.id, onDelete = ReferenceOption.CASCADE
    )
}