package com.example.themovieapp.common.di.app

import com.example.remote.di.service.ApiClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
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
    fun provideRetrofit(serviceObject: com.example.remote.di.service.ApiClient): Retrofit.Builder = serviceObject()

    @AppScope
    @Provides
    fun provideFirebaseAuthInstance() : FirebaseAuth = Firebase.auth

}