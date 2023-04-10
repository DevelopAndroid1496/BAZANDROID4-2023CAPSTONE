package com.example.themovieapp.common.di.app

import com.example.themovieapp.data.ApiClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit


@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @AppScope
    @Provides
    fun provideRetrofit(serviceObject: ApiClient): Retrofit.Builder = serviceObject()

}