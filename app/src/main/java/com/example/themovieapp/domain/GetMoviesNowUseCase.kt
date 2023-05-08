package com.example.themovieapp.domain

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import com.example.themovieapp.common.DataState
import com.example.themovieapp.common.di.app.IoDispatcher
import com.example.themovieapp.common.util.VerifyConnectionNetwork
import com.example.themovieapp.data.repository.MovieRepository
import com.example.themovieapp.domain.model.Movie
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.*

import javax.inject.Inject

class GetMoviesNowUseCase @Inject constructor(
    private val repository: MovieRepository,
    //@ApplicationContext private val context: Context,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) {

    @RequiresApi(Build.VERSION_CODES.M)
    suspend operator fun invoke(): Flow<DataState<List<Movie>>> = flow<DataState<List<Movie>>> {

        //if (VerifyConnectionNetwork.isOnline(context = context)){
            repository.getNowMovies()
                .catch { e -> e.printStackTrace() }
                .collect{
                    emit(it)
                }
        /*}else {
            emit(DataState.Success(repository.getNowMoviesFromDB()))
        }*/

    }.flowOn(ioDispatcher)

}