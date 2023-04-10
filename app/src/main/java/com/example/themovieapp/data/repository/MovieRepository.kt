package com.example.themovieapp.data.repository

import com.example.themovieapp.common.DataState
import com.example.themovieapp.data.model.MovieResponse
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    fun getLatestMovies() : Flow<DataState<MovieResponse>>
}