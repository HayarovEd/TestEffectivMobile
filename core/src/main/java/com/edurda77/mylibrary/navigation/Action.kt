package com.edurda77.mylibrary.navigation

sealed interface Action {
    object HomeToProduct : Action
    object ProductToChart : Action
    object ProductToHome : Action
}