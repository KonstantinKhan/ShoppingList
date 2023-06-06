package ru.shopping_list.be.repo.psql

import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.transactions.transaction

class SqlConnector(
    private val url: String,
    private val driver: String,
    private val user: String,
    private val password: String,
) {
    fun connect(vararg tables: Table): Database {
        val connect = Database.connect(url, driver, user, password)
        transaction(connect) {
            SchemaUtils.create(TgUsersTable, ShoppingListTable, PurchaseTable, StateTable, SharedShoppingListTable)
        }
        return connect
    }
}