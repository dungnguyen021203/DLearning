package com.example.dlearning.domain.usecase.auth

import com.example.dlearning.common.Resource
import com.example.dlearning.domain.repository.AuthRepository
import javax.inject.Inject

class DeleteAccountUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(uid: String): Resource<Unit> {
        return authRepository.deleteAccount(uid)
    }
}