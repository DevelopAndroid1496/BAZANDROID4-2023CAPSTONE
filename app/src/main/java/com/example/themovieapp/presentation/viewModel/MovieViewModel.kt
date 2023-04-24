package com.example.themovieapp.presentation.viewModel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.themovieapp.R
import com.example.themovieapp.common.BaseViewModel
import com.example.themovieapp.common.DataState
import com.example.themovieapp.data.model.latest.LatestMovieResponse
import com.example.themovieapp.data.model.now.MovieResponse
import com.example.themovieapp.domain.GetMovieLatestUseCase
import com.example.themovieapp.domain.GetMoviesNowUseCase
import com.example.themovieapp.domain.GetMoviesTopRatedUseCase
import com.example.themovieapp.domain.ValidateAuthFirebaseUseCase
import com.example.themovieapp.domain.model.Movie
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.intellij.lang.annotations.JdkConstants.ListSelectionMode
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val moviesNowUseCase: GetMoviesNowUseCase,
    private val movieLatestUseCase: GetMovieLatestUseCase,
    private val moviesTopRatedUseCase: GetMoviesTopRatedUseCase,
    private val firebaseUseCase: ValidateAuthFirebaseUseCase
) : BaseViewModel() {

    private val _moviesNow = MutableStateFlow<DataState<List<Movie>>>(DataState.Loading)
    val moviesNow: StateFlow<DataState<List<Movie>>>
        get() = _moviesNow

    private val _moviesLatest = MutableStateFlow<DataState<LatestMovieResponse>>(DataState.Loading)
    val moviesLatest: StateFlow<DataState<LatestMovieResponse>>
        get() = _moviesLatest

    private val _moviesTopRated = MutableStateFlow<DataState<MovieResponse>>(DataState.Loading)
    val moviesTopRated: StateFlow<DataState<MovieResponse>>
        get() = _moviesTopRated

    fun getMoviesNow() {
       viewModelScope.launch {
           moviesNowUseCase()
               .collect{
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

    fun checkUserFirebase(): Boolean = firebaseUseCase.isExistUser()

    fun getInstanceAuthFb (): FirebaseAuth = firebaseUseCase.getInstanceAuthFb()

    fun getGoogleSignInOptionByAuth(ctx: Context): GoogleSignInOptions = firebaseUseCase.authGmailClient(ctx)


}