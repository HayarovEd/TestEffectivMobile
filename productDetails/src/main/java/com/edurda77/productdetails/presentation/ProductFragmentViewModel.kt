package com.edurda77.productdetails.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edurda77.mylibrary.domain.usecases.ProductUseCase
import com.edurda77.mylibrary.domain.usecases.ShopUseCase
import com.edurda77.productdetails.utils.StateProduct
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ProductFragmentViewModel @Inject constructor(
    private val productUseCase: ProductUseCase
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
                    val productData = productUseCase.getProductData()
                    StateProduct.Success(productData)
                } catch (exception: Exception) {
                    StateProduct.Error(exception)
                }
            }
            _productData.value = state
        }
    }
}