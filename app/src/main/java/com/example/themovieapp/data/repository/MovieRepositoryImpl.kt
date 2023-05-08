package com.example.themovieapp.data.repository

import com.example.themovieapp.common.BaseError
import com.example.themovieapp.common.DataState
import com.example.themovieapp.common.di.app.IoDispatcher
import com.example.themovieapp.domain.model.*
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import rx.Observable
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val api: com.example.remote.di.service.MovieApi,
    private val movieDao: com.example.local.db.dao.MovieDao,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : MovieRepository {

    private var entityRequestFromObservable: com.example.local.db.entities.LatestMovieEntity? = null
    override suspend fun getNowMovies(): Flow<DataState<List<Movie>>> = flow {
        try {
            val response = api.getNowMovies()
            if (response.isSuccessful){
                response.body()?.let { nowMovies ->
                    val movies = nowMovies.results.map { it.toDomain() }
                    movieDao.insertAllMoviesToDB(movies.map { it.toDatabaseNow() })
                    emit(value = DataState.Success(data = nowMovies.results.map { it.toDomain() }))
                }
            }
        }catch (e : Exception){ emit(value = DataState.Error(error = BaseError(status_message = "", status_code = -1,status_operation = false, exception = e)))}
    }

    override fun getLatestMovie(): Observable<Response<com.example.remote.di.model.latest.LatestMovieResponse>> {
        return api.getLatestMovie()
    }

    override suspend fun insertMovieLatest(movieLatest: com.example.local.db.entities.LatestMovieEntity) {
        if (entityRequestFromObservable != null){
            movieDao.insertLatestMovie(entityRequestFromObservable!!)
        }
    }

    override suspend fun getMoviesGenders(): Flow<DataState<List<GenderDom>>> = flow {
        try {
            val response = api.getListMoviesGenders()

            if (response.isSuccessful){
                response.body()?.let {
                    movieDao.insertAllGenders(it.toDatabaseGenders())
                    emit(value = DataState.Success(data = it.toDomain()))
                }
            }
        }catch (e: Exception){
            e.printStackTrace()
        }

    }

    override suspend fun getTopRatedMovies(): Flow<DataState<List<Movie>>> = flow{
        try {
            val response = api.getTopRatedMovies()
            if (response.isSuccessful){
                response.body()?.let { topRatedMovies ->
                    emit(value = DataState.Success(data = topRatedMovies.results.map { it.toDomain() }))
                }
            }
        }catch (e : Exception){
            emit(value = DataState.Error(error = BaseError(status_message = "", status_code = -1,status_operation = false, exception = e)))
        }
    }

    override suspend fun getNowMoviesFromDB(): List<Movie> {
        return movieDao.getAllMoviesFromDB().map { it.toDomain() }
    }

    override suspend fun getGendersById(id: Int): Flow<DataState<GenderDom>> = flow{
        emit(value = DataState.Success(data = movieDao.getGendersListWithNowGender(id).toDomain()))
    }


}