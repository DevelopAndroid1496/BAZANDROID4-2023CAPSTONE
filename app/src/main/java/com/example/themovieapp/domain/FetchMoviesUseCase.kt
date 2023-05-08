package com.example.themovieapp.domain

import com.example.themovieapp.common.di.app.IoDispatcher
import com.example.remote.di.model.latest.LatestMovieResponse
import com.example.themovieapp.data.repository.MovieRepository
import kotlinx.coroutines.CoroutineDispatcher
import retrofit2.Response
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers


import javax.inject.Inject

class FetchMoviesUseCase @Inject constructor(
    private val repository: MovieRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) {

   fun fetchMoviesApiFromRx(): Observable<Response<LatestMovieResponse>>{
       return repository.getLatestMovie()
           .subscribeOn(Schedulers.io())
           .observeOn(AndroidSchedulers.mainThread())

   }

}