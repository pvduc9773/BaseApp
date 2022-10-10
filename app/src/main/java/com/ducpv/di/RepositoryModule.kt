package com.ducpv.di

import android.content.Context
import com.ducpv.repository.AuthRepository
import com.ducpv.repository.AuthRepositoryImpl
import com.ducpv.repository.preferstore.PrefsDataStoreRepository
import com.ducpv.repository.preferstore.PrefsDataStoreRepositoryImpl
import com.ducpv.repository.preferstore.dataStore
import com.ducpv.service.AuthService
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by pvduc9773 on 26/07/2022.
 */
@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun providesPrefsDataStoreRepository(gson: Gson, @ApplicationContext context: Context): PrefsDataStoreRepository {
        return PrefsDataStoreRepositoryImpl(gson, context.dataStore)
    }

    @Provides
    @Singleton
    fun provideAuthRepository(authService: AuthService): AuthRepository {
        return AuthRepositoryImpl(authService)
    }
}
