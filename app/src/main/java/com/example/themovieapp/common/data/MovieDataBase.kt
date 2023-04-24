package com.example.themovieapp.common.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.themovieapp.common.data.dao.MovieDao
import com.example.themovieapp.common.data.entities.MovieEntity

@Database(entities = [MovieEntity::class], version = 1)
abstract class MovieDataBase : RoomDatabase() {

    abstract fun getMovieDao(): MovieDao
}