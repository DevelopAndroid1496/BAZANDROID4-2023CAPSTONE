package com.example.themovieapp.data.repository

import com.example.themovieapp.common.DataState
import com.example.themovieapp.data.model.latest.LatestMovieResponse
import com.example.themovieapp.data.model.now.MovieResponse
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    fun getNowMovies() : Flow<DataState<MovieResponse>>

    fun getLatestMovie() : Flow<DataState<LatestMovieResponse>>
}