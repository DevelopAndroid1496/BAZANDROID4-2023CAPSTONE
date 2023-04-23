package com.example.themovieapp.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.themovieapp.R
import com.example.themovieapp.domain.ValidateAuthFirebaseUseCase
import com.example.themovieapp.presentation.viewModel.MovieViewModel
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SingInActivity : AppCompatActivity() {

    private val viewModelMovies : MovieViewModel by viewModels()


    override fun onStart() {
        super.onStart()

        viewModelMovies.createUI()?.let {
            logInResult.launch(it)
        } ?: run {
            showErrorAuth()
        }

    }

    private fun showErrorAuth(){

    }


    private val logInResult = registerForActivityResult(
        FirebaseAuthUIActivityResultContract()
    ) {
        // TODO: save token
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sing_in)

    }
}