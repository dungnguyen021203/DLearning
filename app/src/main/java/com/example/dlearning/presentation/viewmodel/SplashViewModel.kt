package com.example.dlearning.presentation.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dlearning.data.datastore.DataStoreRepository
import com.example.dlearning.utils.ui.ROUTE_LOGIN
import com.example.dlearning.utils.ui.ROUTE_ONBOARDING
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

class SplashViewModel @Inject constructor(
    private val repository: DataStoreRepository
) : ViewModel() {

    private val _isLoading: MutableState<Boolean> = mutableStateOf(true)
    val isLoading: State<Boolean> = _isLoading

    private val _startDestination: MutableState<String> = mutableStateOf(ROUTE_ONBOARDING)
    val startDestination: State<String> = _startDestination

    init {
        viewModelScope.launch {
            val completed = repository.readOnBoardingState().first() // lấy giá trị đầu tiên
            _startDestination.value = if (completed) ROUTE_LOGIN else ROUTE_ONBOARDING
            _isLoading.value = false
        }
    }

}