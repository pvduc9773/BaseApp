package com.ducpv.di

import com.ducpv.BuildConfig
import com.ducpv.repository.preferstore.PrefsDataStoreRepository
import com.ducpv.repository.preferstore.getAccessToken
import com.ducpv.repository.preferstore.getTokenType
import com.ducpv.service.AuthService
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
import retrofit2.converter.scalars.ScalarsConverterFactory
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
            val tokenType = prefsDataStoreRepository.getTokenType() ?: return@runBlocking
            val accessToken = prefsDataStoreRepository.getAccessToken() ?: return@runBlocking
            okHttpClient.addInterceptor(
                Interceptor { chain ->
                    val request: Request = chain
                        .request()
                        .newBuilder()
                        .addHeader(Authorization, tokenType + accessToken)
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
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideAuthService(retrofit: Retrofit): AuthService =
        retrofit.create(AuthService::class.java)
}
