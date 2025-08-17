package com.example.dlearning.presentation.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dlearning.data.datastore.DataStoreRepository
import com.example.dlearning.utils.ui.ROUTE_HOME
import com.example.dlearning.utils.ui.ROUTE_LOGIN
import com.example.dlearning.utils.ui.ROUTE_ONBOARDING
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val repository: DataStoreRepository,
    private val firebaseAuth: FirebaseAuth
) : ViewModel() {

    private val _isLoading: MutableState<Boolean> = mutableStateOf(true)
    val isLoading: State<Boolean> = _isLoading

    private val _startDestination: MutableState<String> = mutableStateOf(ROUTE_ONBOARDING)
    val startDestination: State<String> = _startDestination

    init {
        viewModelScope.launch {
            val completed = repository.readOnBoardingState().first() // lấy giá trị đầu tiên
            _startDestination.value = when {
                !completed -> ROUTE_ONBOARDING
                firebaseAuth.currentUser != null -> ROUTE_HOME
                else -> ROUTE_LOGIN
            }
            _isLoading.value = false
        }
    }

}