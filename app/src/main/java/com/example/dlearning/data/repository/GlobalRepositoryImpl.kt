package com.example.dlearning.data.repository

import com.example.dlearning.common.ApiMapper
import com.example.dlearning.common.Resource
import com.example.dlearning.data.remote.api.GlobalApiService
import com.example.dlearning.data.remote.models.global.GlobalDto
import com.example.dlearning.domain.model.GlobalModel
import com.example.dlearning.domain.repository.GlobalRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class GlobalRepositoryImpl(
    private val globalApiService: GlobalApiService,
    private val apiMapper: ApiMapper<GlobalModel, GlobalDto>
) : GlobalRepository {
    override fun fetchOverallQuestion(): Flow<Resource<GlobalModel>> = flow {
        emit(Resource.Loading)
        val globalDto = globalApiService.fetchOverallQuestion()
        apiMapper.mapToDomain(globalDto).apply {
            emit(Resource.Success(this))
        }
    }.catch { e ->
        emit(Resource.Failure(Exception(e)))
    }

}