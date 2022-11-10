package com.edurda77.testeffectivmobile.navigation

import com.edurda77.mylibrary.navigation.AppNavigation
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
interface NavigationModule {

    @Binds
    fun bindNavController(appNavigationImpl: AppNavigationImpl): AppNavigation

}
