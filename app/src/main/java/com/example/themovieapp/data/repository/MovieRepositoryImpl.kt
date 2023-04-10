package com.example.themovieapp.data.repository

import android.content.Context
import com.example.themovieapp.common.BaseError
import com.example.themovieapp.common.DataState
import com.example.themovieapp.data.model.latest.LatestMovieResponse
import com.example.themovieapp.data.model.now.MovieResponse
import com.example.themovieapp.data.service.MovieApi
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    @ApplicationContext private val ctx: Context,
    private val api: MovieApi
) : MovieRepository {

    override fun getNowMovies(): Flow<DataState<MovieResponse>> = flow{
        try {
            val response = api.getNowMovies()
            if (response.isSuccessful){
                response.body()?.let {
                    emit(value = DataState.Success(data = it))
                }
            }
        }catch (e : Exception){
            emit(value = DataState.Error(error = BaseError(status_message = "", status_code = -1,status_operation = false, exception = e)))
        }
    }

    override fun getLatestMovie(): Flow<DataState<LatestMovieResponse>> = flow {
        try {
            val response = api.getLatestMovie()
            if (response.isSuccessful){
                response.body()?.let {
                    emit(value = DataState.Success(data = it))
                }
            }
        }catch (e : Exception){
            emit(value = DataState.Error(error = BaseError(status_message = "", status_code = -1,status_operation = false, exception = e)))
        }
    }

}