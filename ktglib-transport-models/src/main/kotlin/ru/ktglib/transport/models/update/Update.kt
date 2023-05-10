package ru.ktglib.transport.models.update

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.ktglib.transport.models.Convertible

@Serializable(with = UpdateSerializer::class)
abstract class Update : Convertible {
    @SerialName("update_id")
    abstract val updateId: Int
}