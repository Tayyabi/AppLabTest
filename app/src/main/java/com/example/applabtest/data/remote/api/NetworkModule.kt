package com.example.applabtest.data.remote.api

import com.example.applabtest.data.remote.interceptor.AuthInterceptor
import com.example.applabtest.data.remote.interceptor.ErrorInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object NetworkModule {

    private const val BASE_URL = "https://apim.qweather.app/prod/"

    private fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    private fun provideAuthInterceptor(): AuthInterceptor {
        return AuthInterceptor()
    }

    private fun provideErrorInterceptor(): ErrorInterceptor {
        return ErrorInterceptor()
    }

    private fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(provideAuthInterceptor())
            .addInterceptor(provideErrorInterceptor())
            .addInterceptor(provideHttpLoggingInterceptor())
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    private fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(provideOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun provideWeatherApiService(): WeatherApiService {
        return provideRetrofit().create(WeatherApiService::class.java)
    }
}