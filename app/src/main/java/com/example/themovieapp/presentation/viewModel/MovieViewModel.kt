package com.example.themovieapp.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.themovieapp.common.BaseViewModel
import com.example.themovieapp.common.DataState
import com.example.themovieapp.data.model.MovieResponse
import com.example.themovieapp.domain.GetMoviesLatestUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(private val moviesLatestUseCase: GetMoviesLatestUseCase): BaseViewModel() {

    private val _moviesLatest : MutableLiveData<DataState<MovieResponse>> = MutableLiveData()
    val moviesLatest : LiveData<DataState<MovieResponse>>
        get() = _moviesLatest

    fun getMoviesLatest(){
        viewModelScope.launch {
            moviesLatestUseCase.invoke()
                .collect{
                    _moviesLatest.value = it
                }
        }
    }
}