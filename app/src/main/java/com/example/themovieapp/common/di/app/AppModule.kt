package com.example.themovieapp.common.di.app

import com.example.themovieapp.data.ApiClient
import com.firebase.ui.auth.AuthUI
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.http.POST


@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @AppScope
    @Provides
    fun provideRetrofit(serviceObject: ApiClient): Retrofit.Builder = serviceObject()

    @AppScope
    @Provides
    fun provideFirebaseAuthInstance() : FirebaseAuth = Firebase.auth

}