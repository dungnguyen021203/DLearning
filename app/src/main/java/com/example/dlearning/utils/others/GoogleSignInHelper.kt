package com.example.dlearning.utils.others

import android.content.Context
import androidx.credentials.ClearCredentialStateRequest
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import androidx.credentials.GetCredentialResponse
import com.example.dlearning.common.Resource
import com.example.dlearning.utils.vault.GOOGLE_SERVICE_CLIENT_ID
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import javax.inject.Inject
import kotlin.coroutines.cancellation.CancellationException

class GoogleSignInHelper @Inject constructor(
    private val context: Context,
)  {
    private val credentialManager = CredentialManager.Companion.create(context)

    private suspend fun buildCredentialRequest(): GetCredentialResponse {
        val request = GetCredentialRequest.Builder()
            .addCredentialOption(
                GetGoogleIdOption.Builder()
                    .setFilterByAuthorizedAccounts(false)
                    .setServerClientId(
                        GOOGLE_SERVICE_CLIENT_ID
                    )
                    .setAutoSelectEnabled(false)
                    .build()
            )
            .build()

        return credentialManager.getCredential(
            request = request, context = context
        )
    }

    suspend fun getIdTokenFromGoogle(): Resource<String> {
        return try {
            val result = buildCredentialRequest()
            val credential = result.credential
            if(
                credential is CustomCredential &&
                credential.type == GoogleIdTokenCredential.Companion.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL
            ) {
                val tokenCredential = GoogleIdTokenCredential.Companion.createFrom(credential.data)
                val idToken = tokenCredential.idToken
                Resource.Success(idToken)
            } else {
                Resource.Failure(Exception("Id token ko hợp lệ"))
            }
        } catch (e: Exception) {
            if (e is CancellationException) throw e
            e.printStackTrace()
            Resource.Failure(e)
        }
    }

    suspend fun clearGoogleCredentialState() {
        credentialManager.clearCredentialState(
            ClearCredentialStateRequest()
        )
    }
}