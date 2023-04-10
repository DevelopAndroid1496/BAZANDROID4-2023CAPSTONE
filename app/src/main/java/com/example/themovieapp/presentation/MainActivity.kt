package com.example.themovieapp.presentation

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import com.example.themovieapp.R
import com.example.themovieapp.common.DataState
import com.example.themovieapp.presentation.viewModel.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModelMovies : MovieViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModelMovies.getMoviesLatest()

    }

    override fun onResume() {
        super.onResume()
        setUpObservers()
    }
    private fun setUpObservers(){
        viewModelMovies.moviesLatest.observe(this){
            when(it){
                is DataState.Success -> {
                    //Toast.makeText(this,"Response"+it.data.totalPages,Toast.LENGTH_LONG).show()
                }
                else -> {}
            }
        }
    }
}