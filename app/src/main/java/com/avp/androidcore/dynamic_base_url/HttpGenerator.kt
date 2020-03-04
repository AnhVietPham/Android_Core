package com.avp.androidcore.dynamic_base_url

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object HttpGenerator {
    private var apiBaseUrlDefault = "http://xxxabcv2.com/api/"
    private val mLogging = HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BASIC)
    private val mOkHttpClientBuilder = OkHttpClient.Builder()
        .connectTimeout(60 * 1000, TimeUnit.MILLISECONDS)
        .readTimeout(60 * 1000, TimeUnit.MILLISECONDS)
        .addInterceptor(mLogging)
        .addInterceptor { chain ->
            val request = chain.request().newBuilder()
                .addHeader("Accept", "application/json").build()
            chain.proceed(request)
        }.build()

    fun getHttpGenerator(apiBaseUrl: String = apiBaseUrlDefault): Retrofit {
        return Retrofit.Builder()
            .client(mOkHttpClientBuilder)
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(apiBaseUrl)
            .build()
    }
}