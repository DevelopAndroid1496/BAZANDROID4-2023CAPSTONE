package com.example.themovieapp.domain

import android.annotation.SuppressLint
import com.example.remote.di.model.latest.LatestMovieResponse
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