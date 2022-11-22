package com.edurda77.mylibrary.data.navigation

import androidx.navigation.NavController
import com.edurda77.mylibrary.domain.entity.ItemBest

interface AppNavigation {
    fun execute(action: Action, bestSeller: ItemBest?)
    val navController: NavController
}