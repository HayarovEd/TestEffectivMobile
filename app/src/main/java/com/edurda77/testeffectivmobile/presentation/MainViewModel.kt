package com.edurda77.testeffectivmobile.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edurda77.domain.usecases.CartUseCase
import com.edurda77.domain.usecases.ShopUseCase
import com.edurda77.testeffectivmobile.utils.StateMainActivity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val cartUseCase: com.edurda77.domain.usecases.CartUseCase) : ViewModel() {
    private val _shopData = MutableLiveData<StateMainActivity>(StateMainActivity.Loading)
    val shopData = _shopData

    init {
        getDataFromRepo()
    }

    private fun getDataFromRepo() {
        viewModelScope.launch {
            val state = withContext(Dispatchers.Default) {
                try {
                    val productData = cartUseCase.getCartData()
                    StateMainActivity.Success(productData)
                } catch (exception: Exception) {
                    StateMainActivity.Error(exception)
                }
            }
            _shopData.value = state
        }
    }
}