package ru.ktglib.types

enum class ChatType(val type: String) {
    PRIVATE("private"),
    GROUP("group"),
    SUPERGROUP("supergroup"),
    CHANNEL("channel")
}