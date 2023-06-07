package ru.ktglib.types

import kotlinx.serialization.json.JsonElement

interface Convertible {
    fun toJson(): JsonElement
}