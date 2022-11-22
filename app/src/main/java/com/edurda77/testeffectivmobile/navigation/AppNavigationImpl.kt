package com.edurda77.testeffectivmobile.navigation

import android.app.Activity
import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.edurda77.mylibrary.data.entity.BestSeller
import com.edurda77.mylibrary.data.navigation.Action
import com.edurda77.mylibrary.data.navigation.AppNavigation
import com.edurda77.mylibrary.domain.entity.ItemBest
import com.edurda77.testeffectivmobile.R
import javax.inject.Inject

class AppNavigationImpl @Inject constructor(
    activity: Activity
) : AppNavigation {

    override val navController: NavController by lazy {
        Navigation.findNavController(activity, R.id.nav_host_fragment_activity_main)
    }

    override fun execute(action: Action, bestSeller: ItemBest?) {
            when (action) {
                Action.HomeToProduct -> {
                    val bundle = bundleOf("amount" to bestSeller)
                    navController.navigate(R.id.productFragment, bundle)
                }
                Action.ProductToChart -> {
                    val bundle = Bundle()
                    navController.navigate(R.id.navigation_dashboard, bundle)
                }
                Action.ProductToHome -> {
                    val bundle = Bundle()
                    navController.navigate(R.id.navigation_home, bundle)
                }
            }
    }
}