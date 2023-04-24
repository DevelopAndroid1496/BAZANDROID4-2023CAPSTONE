package com.example.themovieapp.data.repository

import android.content.Context
import com.example.themovieapp.common.BaseError
import com.example.themovieapp.common.DataState
import com.example.themovieapp.common.data.dao.MovieDao
import com.example.themovieapp.data.model.latest.LatestMovieResponse
import com.example.themovieapp.data.model.now.MovieResponse
import com.example.themovieapp.data.service.MovieApi
import com.example.themovieapp.domain.model.Movie
import com.example.themovieapp.domain.model.toDomain
import dagger.hilt.android.qualifiers.ApplicationContext
import io.reactivex.Flowable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import rx.Observable
import rx.Single
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    @ApplicationContext private val ctx: Context,
    private val api: MovieApi,
    private val movieDao: MovieDao
) : MovieRepository {

    override suspend fun getNowMovies(): Flow<DataState<List<Movie>>> = flow {
        try {
            val response = api.getNowMovies()
            if (response.isSuccessful){
                response.body()?.let { nowMovies ->
                    emit(value = DataState.Success(data = nowMovies.results.map { it.toDomain() }))
                }
            }
        }catch (e : Exception){
            emit(value = DataState.Error(error = BaseError(status_message = "", status_code = -1,status_operation = false, exception = e)))
        }
    }

    override suspend fun getLatestMovie(): Flow<DataState<LatestMovieResponse>> = flow {
        try {
            val response = api.getLatestMovie()
            if (response.isSuccessful){
                response.body()?.let { latestMovie ->
                    emit(value = DataState.Success(data = latestMovie))
                }
            }
        }catch (e : Exception){
            emit(value = DataState.Error(error = BaseError(status_message = "", status_code = -1,status_operation = false, exception = e)))
        }
    }

    override suspend fun getTopRatedMovies(): Flow<DataState<MovieResponse>> = flow{
        try {
            val response = api.getTopRatedMovies()
            if (response.isSuccessful){
                response.body()?.let { topRatedMovies ->
                    emit(value = DataState.Success(data = topRatedMovies))
                }
            }
        }catch (e : Exception){
            emit(value = DataState.Error(error = BaseError(status_message = "", status_code = -1,status_operation = false, exception = e)))
        }
    }

    override fun getNowMoviesFromDB(): Flowable<List<Movie>> {
        return movieDao.getAllMoviesFromDB().map { ListaMoviesEntity ->
            ListaMoviesEntity.map { it.toDomain() }
        }
    }


}