package com.example.dlearning.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dlearning.common.Resource
import com.example.dlearning.domain.model.GlobalModel
import com.example.dlearning.domain.repository.GlobalRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GlobalViewModel @Inject constructor(
    private val globalRepository: GlobalRepository
): ViewModel() {
    private val _globalState = MutableStateFlow<Resource<GlobalModel>>(Resource.Loading)
    val globalState: StateFlow<Resource<GlobalModel>> = _globalState.asStateFlow()

    init {
        fetchOverallQuestions()
    }

    fun fetchOverallQuestions() = viewModelScope.launch {
        globalRepository.fetchOverallQuestion()
            .onStart {
                _globalState.value = Resource.Loading
            }
            .catch { e -> _globalState.value = Resource.Failure(e as Exception) }
            .collect { it ->
                _globalState.value = it
            }
    }
}
