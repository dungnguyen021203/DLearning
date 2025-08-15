package com.example.dlearning.domain.repository

import com.example.dlearning.common.Resource
import com.example.dlearning.domain.model.UserModel
import com.google.firebase.auth.FirebaseUser

interface AuthRepository {
    val currentUser: FirebaseUser?

    suspend fun login(email: String, password: String): Resource<FirebaseUser>

    suspend fun signUp(email: String, name: String, password: String): Resource<FirebaseUser>

    suspend fun loginWithGoogle(idToken: String): Resource<FirebaseUser>

    fun logOut()

    suspend fun resetPassword(email: String): Resource<Unit>

    suspend fun getUserInformation(): Resource<UserModel>

    suspend fun changePassword(oldPassword: String, newPassword: String): Resource<Unit>

    suspend fun updateUser(uid: String, updatedUser: UserModel): Resource<Unit>

    suspend fun deleteAccount(uid: String): Resource<Unit>
}