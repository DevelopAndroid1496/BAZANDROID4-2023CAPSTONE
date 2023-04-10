package com.example.themovieapp.data.service

import com.example.themovieapp.data.HEADER_CONTENT_TYPE
import com.example.themovieapp.data.LATEST_MOVIE
import com.example.themovieapp.data.NOW_MOVIES
import com.example.themovieapp.data.model.latest.LatestMovieResponse
import com.example.themovieapp.data.model.now.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface MovieApi {

    @Headers(HEADER_CONTENT_TYPE)
    @GET(NOW_MOVIES)
    suspend fun getNowMovies(): Response<MovieResponse>

    @Headers(HEADER_CONTENT_TYPE)
    @GET(LATEST_MOVIE)
    suspend fun getLatestMovie(): Response<LatestMovieResponse>

}