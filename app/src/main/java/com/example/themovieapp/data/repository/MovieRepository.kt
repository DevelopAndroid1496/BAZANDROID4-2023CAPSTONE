package com.example.themovieapp.data.repository

import com.example.themovieapp.common.DataState
import com.example.themovieapp.common.data.entities.LatestMovieEntity
import com.example.themovieapp.data.model.latest.LatestMovieResponse
import com.example.themovieapp.domain.model.GenderDom
import com.example.themovieapp.domain.model.Movie
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import rx.Observable


interface MovieRepository {

    //By api
    suspend fun getNowMovies() : Flow<DataState<List<Movie>>>

    //By api
    fun getLatestMovie() : Observable<Response<LatestMovieResponse>>

    suspend fun insertMovieLatest(movieLatest: LatestMovieEntity)

    //By api
    suspend fun getTopRatedMovies(): Flow<DataState<List<Movie>>>

    //By api
    suspend fun getMoviesGenders(): Flow<DataState<List<GenderDom>>>

    suspend fun getNowMoviesFromDB(): List<Movie>



    suspend fun getGendersById(id: Int): Flow<DataState<GenderDom>>
}