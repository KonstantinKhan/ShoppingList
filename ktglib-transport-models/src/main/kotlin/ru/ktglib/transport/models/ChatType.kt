package ru.ktglib.transport.models

enum class ChatType(val type: String) {
    PRIVATE("private"),
    GROUP("group"),
    SUPERGROUP("supergroup"),
    CHANNEL("channel")
}