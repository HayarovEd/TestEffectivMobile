package com.edurda77.home.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edurda77.home.utils.StateHome
import com.edurda77.mylibrary.usecases.ShopUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val shopUseCases: ShopUseCases) : ViewModel() {
    private val _shopData = MutableLiveData<StateHome>(StateHome.Loading)
    val shopData = _shopData

    init {
        getDataFromRepo()
    }

    private fun getDataFromRepo() {
        viewModelScope.launch {
            val state = withContext(Dispatchers.Default) {
                try {
                    val shopData = shopUseCases.getShopData()
                    StateHome.Success(shopData)
                } catch (exception: Exception) {
                    StateHome.Error(exception)
                }
            }
            _shopData.value = state
        }
    }
}