package com.example.themovieapp.common.di.data

import com.example.themovieapp.data.repository.MovieRepository
import com.example.themovieapp.data.repository.MovieRepositoryImpl
import com.example.themovieapp.data.service.MovieApi
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import retrofit2.Retrofit

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class DataModule {

    @Binds
    abstract fun bindRepository(movieRepository: MovieRepositoryImpl) : MovieRepository

    companion object {

        @Provides
        fun provideMovieService(retrofit: Retrofit.Builder) : MovieApi =

            retrofit
                .build()
                .create(MovieApi::class.java)
    }
}