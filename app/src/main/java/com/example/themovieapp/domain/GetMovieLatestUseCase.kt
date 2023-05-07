package com.example.themovieapp.domain

import com.example.themovieapp.common.BaseError
import com.example.themovieapp.common.DataState
import com.example.themovieapp.common.data.dao.MovieDao
import com.example.themovieapp.common.data.entities.toDatabaseLatest
import com.example.themovieapp.common.di.app.IoDispatcher
import com.example.themovieapp.data.model.latest.LatestMovieResponse
import com.example.themovieapp.domain.model.toDomain
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetMovieLatestUseCase @Inject constructor(
    private val getAllMoviesFromRxUseCase: GetAllMoviesFromRxUseCase,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val movieDao: MovieDao
) {
    suspend operator fun invoke(): Flow<DataState<LatestMovieResponse>> = flow<DataState<LatestMovieResponse>> {
        val latestMovieResponse = getAllMoviesFromRxUseCase.invoke()?.let {
            it.toDomain()?.let { it1 -> it1.toDatabaseLatest()
                ?.let { it2 -> movieDao.insertLatestMovie(it2) } }
            emit(DataState.Success(data = it))
        } ?: DataState.Error(error = BaseError())

    }.flowOn(ioDispatcher)
}