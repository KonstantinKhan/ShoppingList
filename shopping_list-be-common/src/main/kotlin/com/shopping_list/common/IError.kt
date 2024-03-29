package com.shopping_list.common

interface IError {
    val message: String

    companion object NONE : IError {
        override val message: String = ""
    }
}