package com.example.dlearning.data.remote.api

import com.example.dlearning.data.remote.models.global.GlobalDto
import com.example.dlearning.utils.endpoint.GLOBAL_URL
import retrofit2.http.GET

interface GlobalApiService {
    @GET(GLOBAL_URL)
    suspend fun fetchOverallQuestion(): GlobalDto
}