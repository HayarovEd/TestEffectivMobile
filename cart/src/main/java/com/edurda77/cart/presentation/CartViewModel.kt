package com.edurda77.cart.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edurda77.cart.utils.StateCart
import com.edurda77.mylibrary.network.NetworkRepository
import com.edurda77.mylibrary.network.ResultNetwork
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(private val repository: NetworkRepository) : ViewModel() {
    private val _shopData = MutableLiveData<StateCart>(StateCart.Loading)
    val shopData = _shopData

    init {
        getDataFromRepo()
    }

    private fun getDataFromRepo() {
        viewModelScope.launch {
            when (val repoData = repository.getResponseBasket()) {
                is ResultNetwork.Success -> {}
                is ResultNetwork.SuccessItem -> {}
                is ResultNetwork.SuccessBasket -> {
                    _shopData.value = StateCart.Success(repoData.data)
                }
                is ResultNetwork.Error -> {
                    _shopData.value = StateCart.Error(repoData.error)
                }
            }
        }
    }
}