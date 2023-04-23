package com.example.themovieapp.domain

import android.util.Log
import com.example.themovieapp.common.BaseError
import com.example.themovieapp.common.DataState
import com.example.themovieapp.common.di.app.IoDispatcher
import com.example.themovieapp.data.model.latest.LatestMovieResponse
import com.example.themovieapp.data.repository.MovieRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetMovieLatestUseCase @Inject constructor(
    private val repository: MovieRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) {

    suspend operator fun invoke(): Flow<DataState<LatestMovieResponse>> = flow {
        emit(DataState.Loading)
        repository.getLatestMovie()
            .catch { e ->
                Log.d("EXEP", e.message.toString())
            }
            .collect{ state ->
                when(state){
                    is DataState.Success -> {
                        emit(DataState.Success(data = state.data))
                    }
                    is DataState.Error -> {
                        emit(DataState.Error(error = BaseError(status_message = "Operation not performed")))
                    }
                    else -> {}
                }
            }

    }.flowOn(ioDispatcher)
}