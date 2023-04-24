package com.example.themovieapp.domain

import android.util.Log
import com.example.themovieapp.common.BaseError
import com.example.themovieapp.common.DataState
import com.example.themovieapp.common.di.app.IoDispatcher
import com.example.themovieapp.data.model.now.MovieResponse
import com.example.themovieapp.data.repository.MovieRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetMoviesTopRatedUseCase @Inject constructor(
    private val repository: MovieRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) {

    suspend operator fun invoke() : Flow<DataState<MovieResponse>> = flow{
        emit(DataState.Loading)
        repository.getTopRatedMovies()
            .catch{ e -> e.printStackTrace() }
            .collect{ stateTopMovie -> emit(stateTopMovie) }
    }.flowOn(ioDispatcher)
}