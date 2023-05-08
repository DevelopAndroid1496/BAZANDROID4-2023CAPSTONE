package com.example.remote.di.service

import com.example.remote.di.model.genders.MovieGenderResponse
import com.example.remote.di.model.latest.LatestMovieResponse
import com.example.remote.di.model.now.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import rx.Observable

interface MovieApi {

    @Headers(ApiConstants.HEADER_CONTENT_TYPE)
    @GET(ApiConstants.NOW_MOVIES)
    suspend fun getNowMovies(): Response<MovieResponse>

    @Headers(ApiConstants.HEADER_CONTENT_TYPE)
    @GET(ApiConstants.LATEST_MOVIE)
    fun getLatestMovie(): Observable<Response<LatestMovieResponse>>

    @Headers(ApiConstants.HEADER_CONTENT_TYPE)
    @GET(ApiConstants.TOP_RATED)
    suspend fun getTopRatedMovies() : Response<MovieResponse>

    @Headers(ApiConstants.HEADER_CONTENT_TYPE)
    @GET(ApiConstants.GENDERS)
    suspend fun getListMoviesGenders(): Response<MovieGenderResponse>


}