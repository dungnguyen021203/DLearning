package com.example.dlearning.domain.usecase.auth

import com.example.dlearning.common.Resource
import com.example.dlearning.domain.model.UserModel
import com.example.dlearning.domain.repository.AuthRepository
import javax.inject.Inject

class GetUserInformationUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(): Resource<UserModel> {
        return authRepository.getUserInformation()
    }
}