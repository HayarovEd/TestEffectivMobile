package com.edurda77.home.utils

sealed interface StateCategory {
    object ShowAll: StateCategory
    object ShowPhones: StateCategory
    object ShowComputers: StateCategory
    object ShowHealth: StateCategory
    object ShowBooks: StateCategory
    object ShowOthers: StateCategory
}