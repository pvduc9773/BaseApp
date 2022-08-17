package com.stdio.di

import android.content.Context
import com.google.gson.Gson
import com.stdio.repository.AuthRepository
import com.stdio.repository.AuthRepositoryImpl
import com.stdio.repository.HomeRepository
import com.stdio.repository.HomeRepositoryImpl
import com.stdio.repository.preferstore.PrefsDataStoreRepository
import com.stdio.repository.preferstore.PrefsDataStoreRepositoryImpl
import com.stdio.repository.preferstore.dataStore
import com.stdio.service.AuthService
import com.stdio.service.HomeService
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

    @Provides
    @Singleton
    fun provideHomeRepository(homeService: HomeService): HomeRepository {
        return HomeRepositoryImpl(homeService)
    }
}