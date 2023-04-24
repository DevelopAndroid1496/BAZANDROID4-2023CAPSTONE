package com.example.themovieapp.domain

import com.example.themovieapp.common.DataState
import com.example.themovieapp.common.data.dao.MovieDao
import com.example.themovieapp.common.data.entities.toDatabaseNow
import com.example.themovieapp.common.di.app.IoDispatcher
import com.example.themovieapp.data.repository.MovieRepository
import com.example.themovieapp.domain.model.Movie
import com.example.themovieapp.domain.model.toDomain
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

import javax.inject.Inject

class GetMoviesNowUseCase @Inject constructor(
    private val repository: MovieRepository,
    private val movieDao: MovieDao,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) {

    suspend operator fun invoke(): Flow<DataState<List<Movie>>> = flow {
        repository.getNowMovies()
            .catch { e -> e.printStackTrace() }
            .collect{
                emit(it)
            }
    }.flowOn(ioDispatcher)

    /*fun getListMovies(): List<Movie> {
        var listMovies = emptyList<Movie>()
        repository.getNowMovies()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { responseMovie ->
                if (!responseMovie.isNullOrEmpty()){
                    listMovies = responseMovie
                    movieDao.insertAllMoviesToDB(responseMovie.map { it.toDatabaseNow() })
                }else{
                    listMovies = movieDao.getAllMoviesFromDB().map { it.toDomain() }
                }

            }
        return listMovies
    }*/
}