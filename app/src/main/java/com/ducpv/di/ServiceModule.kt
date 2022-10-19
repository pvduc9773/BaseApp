package com.ducpv.di

import com.ducpv.BuildConfig
import com.ducpv.repository.preferstore.PrefsDataStoreRepository
import com.ducpv.service.Service
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Created by pvduc9773 on 26/07/2022.
 */
@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {
    private const val Authorization = "Authorization"

    @Provides
    @Singleton
    fun provideOkHttpClient(
        prefsDataStoreRepository: PrefsDataStoreRepository
    ): OkHttpClient {
        val okHttpClient = OkHttpClient.Builder()
        val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }
        okHttpClient.addInterceptor(httpLoggingInterceptor)
        runBlocking {
            val accessToken = "accessToken" // prefsDataStoreRepository.getAccessToken() ?: return@runBlocking
            okHttpClient.addInterceptor(
                Interceptor { chain ->
                    val request: Request = chain
                        .request()
                        .newBuilder()
                        .addHeader(Authorization, accessToken)
                        .build()
                    chain.proceed(request)
                }
            )
        }
        return okHttpClient
            .connectTimeout(30L, TimeUnit.SECONDS)
            .readTimeout(30L, TimeUnit.SECONDS)
            .writeTimeout(30L, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideService(retrofit: Retrofit): Service =
        retrofit.create(Service::class.java)
}
