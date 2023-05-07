package com.example.themovieapp.data.service


import com.example.themovieapp.data.*
import com.example.themovieapp.data.model.genders.MovieGenderResponse
import com.example.themovieapp.data.model.latest.LatestMovieResponse
import com.example.themovieapp.data.model.now.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import rx.Observable

interface MovieApi {

    @Headers(HEADER_CONTENT_TYPE)
    @GET(NOW_MOVIES)
    suspend fun getNowMovies(): Response<MovieResponse>

    @Headers(HEADER_CONTENT_TYPE)
    @GET(LATEST_MOVIE)
    fun getLatestMovie(): Observable<Response<LatestMovieResponse>>

    @Headers(HEADER_CONTENT_TYPE)
    @GET(TOP_RATED)
    suspend fun getTopRatedMovies() : Response<MovieResponse>

    @Headers(HEADER_CONTENT_TYPE)
    @GET(GENDERS)
    suspend fun getListMoviesGenders(): Response<MovieGenderResponse>


}