package com.example.themovieapp.data.repository

import com.example.themovieapp.common.DataState
import com.example.themovieapp.data.model.latest.LatestMovieResponse
import com.example.themovieapp.data.model.now.MovieResponse
import com.example.themovieapp.domain.model.Movie
import io.reactivex.Flowable
import kotlinx.coroutines.flow.Flow
import rx.Single

interface MovieRepository {

    suspend fun getNowMovies() : Flow<DataState<List<Movie>>>

    suspend fun getLatestMovie() : Flow<DataState<LatestMovieResponse>>

    suspend fun getTopRatedMovies(): Flow<DataState<MovieResponse>>

    fun getNowMoviesFromDB(): Flowable<List<Movie>>
}