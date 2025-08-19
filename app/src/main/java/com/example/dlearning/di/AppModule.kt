package com.example.dlearning.di

import android.content.Context
import com.example.dlearning.common.ApiMapper
import com.example.dlearning.data.datastore.DataStoreRepository
import com.example.dlearning.data.mapper.GlobalApiMapperImpl
import com.example.dlearning.data.remote.api.GlobalApiService
import com.example.dlearning.data.remote.models.global.GlobalDto
import com.example.dlearning.data.repository.AuthRepositoryImpl
import com.example.dlearning.data.repository.GlobalRepositoryImpl
import com.example.dlearning.domain.model.GlobalModel
import com.example.dlearning.domain.repository.AuthRepository
import com.example.dlearning.domain.repository.GlobalRepository
import com.example.dlearning.utils.others.GoogleSignInHelper
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun provideFirebaseFirestore(): FirebaseFirestore = FirebaseFirestore.getInstance()

    @Provides
    @Singleton
    fun provideGoogleSignInHelper(@ApplicationContext context: Context): GoogleSignInHelper {
        return GoogleSignInHelper(context)
    }

    @Provides
    @Singleton
    fun provideGlobalMapper(): ApiMapper<GlobalModel, GlobalDto> = GlobalApiMapperImpl()

    @Provides
    @Singleton
    fun provideAuthRepository(
        firebaseAuth: FirebaseAuth,
        firebaseFirestore: FirebaseFirestore
    ): AuthRepository {
        return AuthRepositoryImpl(firebaseAuth, firebaseFirestore)
    }

    @Provides
    @Singleton
    fun provideDataStoreRepository(@ApplicationContext context: Context) =
        DataStoreRepository(context = context)

    @Provides
    @Singleton
    fun provideGlobalRepository(
        globalApiService: GlobalApiService,
        mapper: ApiMapper<GlobalModel, GlobalDto>
    ): GlobalRepository = GlobalRepositoryImpl (
        globalApiService = globalApiService, apiMapper = mapper
    )
}