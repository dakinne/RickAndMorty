package com.noox.rickandmorty.core.di

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.noox.rickandmorty.BuildConfig
import com.noox.rickandmorty.core.api.ApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

const val CONNECTION_TIMEOUT = 20L

val coreModule = module {

    single { okHttpClient() }
    single { apiService(okHttpClient = get()) }

}

private fun okHttpClient(): OkHttpClient {

    val okHttpClientBuilder = OkHttpClient.Builder()
        .readTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
        .connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)

    if (BuildConfig.DEBUG) {
        okHttpClientBuilder.addInterceptor(
            HttpLoggingInterceptor().apply { level = Level.BODY }
        )
    }

    return okHttpClientBuilder.build()
}

private fun apiService(
    okHttpClient: OkHttpClient
) = Retrofit.Builder()
    .baseUrl(BuildConfig.API_URL)
    .callFactory(okHttpClient)
    .addConverterFactory(MoshiConverterFactory.create())
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .client(okHttpClient)
    .build()
    .create(ApiService::class.java)
