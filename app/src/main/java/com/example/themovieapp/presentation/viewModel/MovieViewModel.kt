package com.example.themovieapp.presentation.viewModel

import android.content.Context
import android.os.Build
import android.provider.ContactsContract.Data
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.themovieapp.R
import com.example.themovieapp.common.BaseViewModel
import com.example.themovieapp.common.DataState
import com.example.remote.di.model.genders.GenderMovie
import com.example.remote.di.model.genders.MovieGenderResponse
import com.example.remote.di.model.latest.LatestMovieResponse
import com.example.remote.di.model.now.MovieResponse
import com.example.themovieapp.domain.*
import com.example.themovieapp.domain.model.GenderDom
import com.example.themovieapp.domain.model.Movie
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.intellij.lang.annotations.JdkConstants.ListSelectionMode
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val moviesNowUseCase: GetMoviesNowUseCase,
    private val movieLatestUseCase: GetMovieLatestUseCase,
    private val moviesTopRatedUseCase: GetMoviesTopRatedUseCase,
    private val firebaseUseCase: ValidateAuthFirebaseUseCase,
    private val genderUseCase: GetAllGenderesMoviesUseCase
) : BaseViewModel() {

    init {

    }

    private val _moviesNow = MutableStateFlow<DataState<List<Movie>>>(DataState.Loading)
    val moviesNow: StateFlow<DataState<List<Movie>>>
        get() = _moviesNow

    private val _moviesLatest = MutableStateFlow<DataState<com.example.remote.di.model.latest.LatestMovieResponse>>(DataState.Loading)
    val moviesLatest: StateFlow<DataState<com.example.remote.di.model.latest.LatestMovieResponse>>
        get() = _moviesLatest

    private val _moviesTopRated = MutableStateFlow<DataState<List<Movie>>>(DataState.Loading)
    val moviesTopRated: StateFlow<DataState<List<Movie>>>
        get() = _moviesTopRated

    private val _moviesGenders = MutableStateFlow<DataState<List<GenderDom>>>(DataState.Loading)
    val movieGenders : StateFlow<DataState<List<GenderDom>>>
        get() = _moviesGenders

    private val _gendersFromDB = MutableStateFlow<DataState<GenderDom>>(DataState.Loading)
    val gendersFromDB : StateFlow<DataState<GenderDom>>
        get() = _gendersFromDB

    @RequiresApi(Build.VERSION_CODES.M)
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

    fun getGendersMovies(){
        viewModelScope.launch {
            genderUseCase.invoke()
                .collect{
                    _moviesGenders.value = it
                }
        }
    }

    fun collectIdBygetGenders(id: Int){
        viewModelScope.launch {
            genderUseCase.getGendersFromDB(id)
                .collect{
                    _gendersFromDB.value = it
                }
        }
    }

    fun checkUserFirebase(): Boolean = firebaseUseCase.isExistUser()

    fun getInstanceAuthFb (): FirebaseAuth = firebaseUseCase.getInstanceAuthFb()

    fun getGoogleSignInOptionByAuth(ctx: Context): GoogleSignInOptions = firebaseUseCase.authGmailClient(ctx)


}