package com.edurda77.home.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edurda77.home.utils.StateHome
import com.edurda77.mylibrary.network.NetworkRepository
import com.edurda77.mylibrary.network.ResultNetwork
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: NetworkRepository) : ViewModel() {
    private val _shopData = MutableLiveData<StateHome>(StateHome.Loading)
    val shopData = _shopData

    init {
        getDataFromRepo()
    }

    private fun getDataFromRepo() {
        viewModelScope.launch {
            when (val repoData = repository.getResponseAllData()) {
                is ResultNetwork.Success -> {
                    _shopData.value = StateHome.Success(repoData.data)
                }
                is ResultNetwork.SuccessItem -> {}
                is ResultNetwork.Error -> {
                    _shopData.value = StateHome.Error(repoData.error)
                }
            }
        }
    }
}