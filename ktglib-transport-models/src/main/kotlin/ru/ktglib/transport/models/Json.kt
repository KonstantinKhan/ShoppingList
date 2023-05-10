package ru.ktglib.transport.models

import kotlinx.serialization.json.Json

object Json {
    val json = Json {
        ignoreUnknownKeys = true
    }
}