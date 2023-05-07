package com.example.themovieapp.domain

import android.annotation.SuppressLint
import com.example.themovieapp.common.data.dao.MovieDao
import com.example.themovieapp.common.data.entities.toDatabaseLatest
import com.example.themovieapp.data.model.latest.LatestMovieResponse
import com.example.themovieapp.domain.model.toDomain
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class GetAllMoviesFromRxUseCase @Inject constructor(private val fetchMoviesUseCase: FetchMoviesUseCase)  {

    @SuppressLint("CheckResult")
    suspend operator fun invoke(): LatestMovieResponse? =
        suspendCoroutine { continuation ->
            fetchMoviesUseCase.fetchMoviesApiFromRx()
                .subscribe( {
                    continuation.resume(it.body())
                },{
                    continuation.resume(null)
                })


        }

}