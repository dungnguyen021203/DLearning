package com.example.dlearning.domain.repository

import com.example.dlearning.common.Resource
import com.example.dlearning.domain.model.GlobalModel
import kotlinx.coroutines.flow.Flow

interface GlobalRepository {
    fun fetchOverallQuestion(): Flow<Resource<GlobalModel>>
}