package com.example.themovieapp.common.data


import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.themovieapp.common.data.dao.MovieDao
import com.example.themovieapp.common.data.entities.*

@Database(
    entities = [MovieEntity::class,
        LatestMovieEntity::class,
        TopRatedMovieEntity::class,
               GenderMovieEntity::class], version = 1
)
@TypeConverters(Converter::class)
abstract class MovieDataBase : RoomDatabase() {

    abstract fun getMovieDao(): MovieDao
}