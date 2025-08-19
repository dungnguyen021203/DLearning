package com.example.dlearning.di

import com.example.dlearning.data.remote.api.GlobalApiService
import com.example.dlearning.domain.repository.CloudinaryRepository
import com.example.dlearning.utils.endpoint.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.cloudinary.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideCloudinaryRepository(retrofit: Retrofit): CloudinaryRepository {
        return retrofit.create(CloudinaryRepository::class.java)
    }

    @Provides
    @Singleton
    fun provideGlobalApiService(): GlobalApiService {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GlobalApiService::class.java)
    }
}