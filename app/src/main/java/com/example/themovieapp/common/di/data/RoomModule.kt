package com.example.themovieapp.common.di.data

import android.content.Context
import androidx.room.Room
import com.example.themovieapp.common.data.MovieDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    private const val DATABASE_NAME = "the_movie_db"

    @Singleton
    @Provides
    fun providesRoom(@ApplicationContext context: Context) = Room.databaseBuilder(context,MovieDataBase::class.java,
        DATABASE_NAME).build()

    @Singleton
    @Provides
    fun provideMovieDao(db: MovieDataBase) = db.getMovieDao()



}