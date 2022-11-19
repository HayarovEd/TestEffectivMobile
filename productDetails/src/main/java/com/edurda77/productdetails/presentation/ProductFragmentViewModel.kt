package com.edurda77.productdetails.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edurda77.mylibrary.usecases.ShopUseCases
import com.edurda77.productdetails.utils.StateProduct
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ProductFragmentViewModel @Inject constructor(
    private val shopUseCases: ShopUseCases
) : ViewModel() {
    private val _productData = MutableLiveData<StateProduct>(StateProduct.Loading)
    val productData = _productData

    init {
        getDataFromRepo()
    }

    private fun getDataFromRepo() {
        viewModelScope.launch {
            val state = withContext(Dispatchers.Default) {
                try {
                    val productData = shopUseCases.getProductData()
                    StateProduct.Success(productData)
                } catch (exception: Exception) {
                    StateProduct.Error(exception)
                }
            }
            _productData.value = state
        }
    }
}