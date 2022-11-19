package com.edurda77.mylibrary.navigation

import androidx.navigation.NavController
import com.edurda77.mylibrary.data.dto.BestSeller

interface AppNavigation {
    fun execute(action: Action, bestSeller: BestSeller?)
    val navController: NavController
}