package com.example.themovieapp.data.repository

import android.content.Context
import com.example.themovieapp.common.DataState
import com.example.themovieapp.data.model.MovieResponse
import com.example.themovieapp.data.service.MovieApi
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    @ApplicationContext private val ctx: Context,
    private val api: MovieApi
) : MovieRepository {

    override suspend fun getLatestMovies(): Flow<DataState<MovieResponse>> = flow{
        try {
            val response = api.getLatestMovies()
            if (response.isSuccessful){
                response.body()?.let { emit(value = DataState.Success(data = it)) }
            }
        }catch (_: Exception){

        }
    }

}