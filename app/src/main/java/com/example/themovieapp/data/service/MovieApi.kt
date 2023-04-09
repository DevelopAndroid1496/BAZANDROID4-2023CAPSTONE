package com.example.themovieapp.data.service

import com.example.themovieapp.data.HEADER_CONTENT_TYPE
import com.example.themovieapp.data.NOW_MOVIES
import com.example.themovieapp.data.model.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface MovieApi {

    @Headers(HEADER_CONTENT_TYPE)
    @GET(NOW_MOVIES)
    suspend fun getLatestMovies(): Response<MovieResponse>

}