package com.example.remote.di.service

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class ApiClient @Inject constructor() {

    operator fun invoke (): Retrofit.Builder{
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)


        val okHttpClientBuilder = OkHttpClient().newBuilder()
        okHttpClientBuilder.interceptors().clear()

        okHttpClientBuilder
            .addInterceptor(interceptor)
            .readTimeout(2, TimeUnit.MINUTES)
            .writeTimeout(2, TimeUnit.MINUTES)
            .connectTimeout(2, TimeUnit.MINUTES)
            .retryOnConnectionFailure(false)
        val client: OkHttpClient = okHttpClientBuilder.build()

        return Retrofit.Builder()
            .baseUrl(ApiConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .client(client)

    }

}