package com.example.themovieapp.common.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.themovieapp.common.data.entities.MovieEntity
import io.reactivex.Flowable
import rx.Observable
import rx.Single

@Dao
interface MovieDao {
    @Query("SELECT * FROM movie_table")
    fun getAllMoviesFromDB(): Flowable<List<MovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllMoviesToDB(movie: List<MovieEntity>)
}