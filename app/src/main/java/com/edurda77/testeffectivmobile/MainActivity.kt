package com.edurda77.testeffectivmobile

import android.os.Bundle
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.edurda77.mylibrary.data.navigation.AppNavigation
import com.edurda77.testeffectivmobile.databinding.ActivityMainBinding
import com.edurda77.testeffectivmobile.presentation.MainViewModel
import com.edurda77.testeffectivmobile.utils.StateMainActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navView: BottomNavigationView
    private val viewModel by viewModels<MainViewModel>()
    @Inject
    lateinit var coordinator: AppNavigation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications, R.id.productFragment
            )
        )
        val badge = navView.getOrCreateBadge(R.id.navigation_dashboard)
        viewModel.shopData.observe(this){
            when (it) {
                is StateMainActivity.Success -> {
                    if (it.data.basket.isEmpty()) {
                        badge.isVisible = false
                    } else {
                        badge.isVisible = true
                        badge.number = it.data.basket.size
                    }
                }
                is StateMainActivity.Error -> {
                    Toast.makeText(this, it.error.message, Toast.LENGTH_LONG).show()
                }
                is StateMainActivity.Loading -> {}
            }
        }
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        supportActionBar?.hide()
        coordinator.navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.navigation_home -> {
                    showBottomNav()
                }
                R.id.navigation_notifications -> {
                    showBottomNav()
                }
                else -> {
                    hideBottomNav()
                }
            }
        }
        //
    // setSizeHomeIcon()
    }

    private fun showBottomNav() {
        navView.visibility = View.VISIBLE
    }

    private fun hideBottomNav() {
        navView.visibility = View.GONE
    }

    private fun setSizeHomeIcon() {
        val iconView = navView.getChildAt(0)
        val displayMetrics = resources.displayMetrics
        val layoutParams : ViewGroup.LayoutParams = iconView.layoutParams
        layoutParams.height =
            TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 45f, displayMetrics).toInt()
        layoutParams.width =
            TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 45f, displayMetrics).toInt()
        iconView.layoutParams = layoutParams
    }
}