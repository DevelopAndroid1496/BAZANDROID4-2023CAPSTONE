package com.example.themovieapp.data

import com.example.themovieapp.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

class ApiClient {

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
            .client(client)

    }

}