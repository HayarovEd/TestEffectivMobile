package com.edurda77.cart.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edurda77.cart.utils.StateCart
import com.edurda77.mylibrary.domain.usecases.CartUseCase
import com.edurda77.mylibrary.domain.usecases.ShopUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(private val cartUseCase: CartUseCase) : ViewModel() {
    private val _shopData = MutableLiveData<StateCart>(StateCart.Loading)
    val shopData = _shopData

    init {
        getDataFromRepo()
    }

    private fun getDataFromRepo() {
        viewModelScope.launch {
            val state = withContext(Dispatchers.Default) {
                try {
                    val productData = cartUseCase.getCartData()
                    StateCart.Success(productData)
                } catch (exception: Exception) {
                    StateCart.Error(exception)
                }
            }
            _shopData.value = state
        }
    }
}