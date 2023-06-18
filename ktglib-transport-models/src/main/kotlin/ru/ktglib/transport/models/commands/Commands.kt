package ru.ktglib.transport.models.commands

enum class Commands(val value: String) {
    START("/start"),
    HELP("/help"),
    CLEAR("/clear"),
    TIDY("/tidy"),
    RELATED("/related"),
    CHOOSE("/choose")
}