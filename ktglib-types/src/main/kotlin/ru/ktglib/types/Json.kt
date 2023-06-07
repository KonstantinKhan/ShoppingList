package ru.ktglib.types

import kotlinx.serialization.json.Json

object Json {
    val json = Json {
        ignoreUnknownKeys = true
    }
}