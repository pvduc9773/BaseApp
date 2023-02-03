package com.ducpv.di

import android.content.Context
import com.ducpv.repository.Repository
import com.ducpv.repository.RepositoryImpl
import com.ducpv.preferstore.PrefsDataStoreRepository
import com.ducpv.preferstore.PrefsDataStoreRepositoryImpl
import com.ducpv.preferstore.dataStore
import com.ducpv.service.Service
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
    fun provideAuthRepository(service: Service): Repository {
        return RepositoryImpl(service)
    }
}
