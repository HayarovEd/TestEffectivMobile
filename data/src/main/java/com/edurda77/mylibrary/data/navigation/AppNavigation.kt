package com.edurda77.mylibrary.data.navigation

import androidx.navigation.NavController
import com.edurda77.domain.entity.ItemBest

interface AppNavigation {
    fun execute(action: Action, bestSeller: com.edurda77.domain.entity.ItemBest?)
    val navController: NavController
}