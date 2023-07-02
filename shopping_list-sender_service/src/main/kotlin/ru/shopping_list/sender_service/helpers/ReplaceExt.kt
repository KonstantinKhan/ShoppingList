package ru.shopping_list.sender_service.helpers

fun String.replaceExt(): String {
    val builder: StringBuilder = StringBuilder()

    this.forEach {
        if (ReplaceableCharacters.characters.contains(it))
            builder.append('\\')
        builder.append(it)
    }
    return builder.toString()
}