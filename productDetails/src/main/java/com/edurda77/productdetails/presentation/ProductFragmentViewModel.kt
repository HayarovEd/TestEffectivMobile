package com.edurda77.productdetails.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edurda77.mylibrary.network.NetworkRepository
import com.edurda77.mylibrary.network.ResultNetwork
import com.edurda77.productdetails.utils.StateProduct
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductFragmentViewModel @Inject constructor(
    private val repository: NetworkRepository
) : ViewModel() {
    private val _productData = MutableLiveData<StateProduct>(StateProduct.Loading)
    val productData = _productData

    init {
        getDataFromRepo()
    }

    private fun getDataFromRepo() {
        viewModelScope.launch {
            when (val repoData = repository.getResponseItem()) {
                is ResultNetwork.Success -> {}
                is ResultNetwork.SuccessItem -> {
                    _productData.value = StateProduct.Success(repoData.data)
                }
                is ResultNetwork.Error -> {
                    _productData.value = StateProduct.Error(repoData.error)
                }
            }
        }
    }
}