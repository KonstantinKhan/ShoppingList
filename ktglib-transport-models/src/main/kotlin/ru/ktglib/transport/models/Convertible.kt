package ru.ktglib.transport.models

import kotlinx.serialization.json.JsonElement

interface Convertible {
    fun toJson(): JsonElement
}