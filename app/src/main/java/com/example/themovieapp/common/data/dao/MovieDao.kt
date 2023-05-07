package com.example.themovieapp.common.data.dao

import androidx.room.*
import com.example.themovieapp.common.data.entities.*
import com.example.themovieapp.data.model.genders.GenderMovie
import com.example.themovieapp.domain.model.GenderDom


@Dao
interface MovieDao {
    @Query("SELECT * FROM movie_now_table")
    suspend fun getAllMoviesFromDB(): List<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllMoviesToDB(movie: List<MovieEntity>)




    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLatestMovie(latestMovie: LatestMovieEntity)//



    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTopRatedMovie(topRatedMovies: List<TopRatedMovieEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllGenders(genderList: List<GenderMovieEntity>)

    @Query("SELECT * FROM gender_movie_table where id= :id")
    suspend fun getGendersListWithNowGender(id: Int): GenderMovieEntity
}