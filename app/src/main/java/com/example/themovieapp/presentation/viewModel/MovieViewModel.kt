package com.example.themovieapp.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.themovieapp.common.BaseViewModel
import com.example.themovieapp.common.DataState
import com.example.themovieapp.data.model.latest.LatestMovieResponse
import com.example.themovieapp.data.model.now.MovieResponse
import com.example.themovieapp.domain.GetMovieLatestUseCase
import com.example.themovieapp.domain.GetMoviesNowUseCase
import com.example.themovieapp.domain.GetMoviesTopRatedUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val moviesNowUseCase: GetMoviesNowUseCase,
    private val movieLatestUseCase: GetMovieLatestUseCase,
    private val moviesTopRatedUseCase: GetMoviesTopRatedUseCase
) : BaseViewModel() {

    private val _moviesNow: MutableLiveData<DataState<MovieResponse>> = MutableLiveData()
    val moviesNow: LiveData<DataState<MovieResponse>>
        get() = _moviesNow

    private val _moviesLatest: MutableLiveData<DataState<LatestMovieResponse>> = MutableLiveData()
    val moviesLatest: LiveData<DataState<LatestMovieResponse>>
        get() = _moviesLatest

    private val _moviesTopRated: MutableLiveData<DataState<MovieResponse>> = MutableLiveData()
    val moviesTopRated: LiveData<DataState<MovieResponse>>
        get() = _moviesTopRated

    fun getMoviesNow() {
        viewModelScope.launch {
            moviesNowUseCase.invoke()
                .collect {
                    _moviesNow.value = it
                }
        }
    }

    fun getMovieLatest() {
        viewModelScope.launch {
            movieLatestUseCase.invoke()
                .collect{
                    _moviesLatest.value = it
                }
        }
    }

    fun getMovieTopRated() {
        viewModelScope.launch {
            moviesTopRatedUseCase.invoke()
                .collect{
                    _moviesTopRated.value = it
                }
        }
    }

}