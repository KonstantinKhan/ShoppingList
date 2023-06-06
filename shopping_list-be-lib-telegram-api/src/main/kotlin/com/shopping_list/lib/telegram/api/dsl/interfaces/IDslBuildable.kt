package com.shopping_list.lib.telegram.api.dsl.interfaces

interface IDslBuildable<T> {
    fun build(): T
}