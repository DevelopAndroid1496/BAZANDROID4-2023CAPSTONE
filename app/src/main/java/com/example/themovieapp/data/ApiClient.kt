package com.example.themovieapp.data

import com.example.themovieapp.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class ApiClient @Inject constructor() {

    operator fun invoke (): Retrofit.Builder{
        val interceptor = HttpLoggingInterceptor()

        val okHttpClientBuilder = OkHttpClient().newBuilder()
        okHttpClientBuilder.interceptors().clear()

        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        okHttpClientBuilder
            .addInterceptor(interceptor)
            .readTimeout(2, TimeUnit.MINUTES)
            .writeTimeout(2, TimeUnit.MINUTES)
            .connectTimeout(2, TimeUnit.MINUTES)
            .retryOnConnectionFailure(false)
            .addInterceptor(interceptor)
        val client: OkHttpClient = okHttpClientBuilder.build()

        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)

    }

}