package com.example.dlearning.data.repository

import com.example.dlearning.common.Resource
import com.example.dlearning.domain.repository.CloudinaryRepository
import com.example.dlearning.utils.vault.UPLOAD_PRESET
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import javax.inject.Inject

class CloudinaryRepositoryImpl @Inject constructor(
    private val cloudinaryRepository: CloudinaryRepository
) {
    suspend fun uploadAvatar(file: File): Resource<String> { //Upload to Cloudinary
        return try {
            val requestBody = file.asRequestBody("image/*".toMediaType())
            val multipart = MultipartBody.Part.createFormData("file", file.name, requestBody)
            val preset = RequestBody.create("text/plain".toMediaType(), UPLOAD_PRESET)

            val response = cloudinaryRepository.uploadImage(multipart, preset)
            val url = response.secureUrl
            if (!url.isNullOrEmpty()) {
                Resource.Success(url)
            } else {
                Resource.Failure(Exception("secureUrl is null or empty"))
            }
        } catch (e: Exception) {
            Resource.Failure(e)
        }
    }
}