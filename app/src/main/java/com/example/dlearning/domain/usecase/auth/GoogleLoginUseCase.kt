package com.example.dlearning.domain.usecase.auth

import com.example.dlearning.common.Resource
import com.example.dlearning.domain.model.UserModel
import com.example.dlearning.domain.repository.AuthRepository
import com.example.dlearning.utils.others.await
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class GoogleLoginUseCase @Inject constructor(
    private val authRepository: AuthRepository,
    private val firestore: FirebaseFirestore
) {
    suspend operator fun invoke(idToken: String): Resource<FirebaseUser> {
        val result = authRepository.loginWithGoogle(idToken)

        if (result is Resource.Success) {
            val user = result.data
            val userRef = firestore.collection("users").document(user.uid)

            // Check if user already exists in Firestore
            val document = userRef.get().await()
            if(!document.exists()) {
                val userModel = UserModel(
                    uid = user.uid,
                    name = user.displayName ?: "",
                    email = user.email ?: "",
                )
                try {
                    userRef.set(userModel).await()
                } catch (e: Exception) {
                    return Resource.Failure(e)
                }
            }
        }

        return result
    }
}