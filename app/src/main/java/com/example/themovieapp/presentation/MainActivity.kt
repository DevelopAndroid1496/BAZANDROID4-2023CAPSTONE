package com.example.themovieapp.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.themovieapp.R
import com.example.themovieapp.common.DataState
import com.example.themovieapp.presentation.viewModel.MovieViewModel
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    private val viewModelMovies : MovieViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //viewModelMovies.getMoviesNow()
        //viewModelMovies.getMovieLatest()
        viewModelMovies.getMovieTopRated()



    }

    override fun onResume() {
        super.onResume()
        setUpObservers()
    }
    private fun setUpObservers(){
        /*viewModelMovies.moviesLatest.observe(this){
            when(it){
                is DataState.Success -> {
                    //Toast.makeText(this,"Response"+it.data.totalPages,Toast.LENGTH_LONG).show()
                }
                else -> {}
            }
        }*/
        /*viewModelMovies.moviesLatest.observe(this){
            when(it){
                is DataState.Success -> {
                    Toast.makeText(this,"Response "+it.data.originalTitle, Toast.LENGTH_LONG).show()
                }
                else -> {}
            }
        }*/

        viewModelMovies.moviesTopRated.observe(this){
            when(it){
                is DataState.Success -> {
                    Toast.makeText(this,"Response "+it.data.totalResults, Toast.LENGTH_LONG).show()
                }
                else -> {}
            }
        }
    }
}