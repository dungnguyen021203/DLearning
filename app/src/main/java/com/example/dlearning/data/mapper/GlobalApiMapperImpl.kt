package com.example.dlearning.data.mapper

import android.util.Log
import com.example.dlearning.common.ApiMapper
import com.example.dlearning.data.remote.models.global.GlobalDto
import com.example.dlearning.domain.model.GlobalModel

class GlobalApiMapperImpl: ApiMapper<GlobalModel, GlobalDto> {
    override fun mapToDomain(apiDto: GlobalDto): GlobalModel {

        return GlobalModel(
            totalNumOfQuestions = apiDto.overall?.totalNumOfQuestions ?: 0
        )
    }
}