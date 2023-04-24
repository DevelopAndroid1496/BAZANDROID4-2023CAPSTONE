package com.example.themovieapp.presentation.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.whenStarted
import com.example.themovieapp.BuildConfig
import com.example.themovieapp.R
import com.example.themovieapp.common.DataState
import com.example.themovieapp.data.PATH_IMAGES
import com.example.themovieapp.data.model.latest.LatestMovieResponse
import com.example.themovieapp.databinding.FragmentLatestMovieBinding
import com.example.themovieapp.presentation.viewModel.MovieViewModel
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.lang.Exception

@AndroidEntryPoint
class LatestMovieFragment : Fragment() {

    private var _binding: FragmentLatestMovieBinding? = null
    private val binding get() = _binding
    private val viewModel : MovieViewModel by viewModels()
    private var uiJob: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getMovieLatest()
    }

    override fun onStart() {
        super.onStart()
        fetchLatestMovie()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentLatestMovieBinding.inflate(inflater,container,false)
        viewModel.getMovieLatest()
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun fetchLatestMovie(){
        uiJob = lifecycleScope.launch{
            whenStarted {
                viewModel.moviesLatest.collect{ latestState ->
                    when(latestState){
                        is DataState.Loading -> {

                        }
                        is DataState.Success -> {
                            updateLatestMovieUI(latestState.data)
                        }
                        is DataState.Error -> {

                        }
                        else -> {}
                    }
                }
            }
        }
    }

    private fun updateLatestMovieUI(latestResponse : LatestMovieResponse){
        if (latestResponse.posterPath != null){
            Picasso.get()
                .load(BuildConfig.BASE_URL_IMAGES.plus(PATH_IMAGES).plus(latestResponse.posterPath))
                .centerCrop()
                .into(binding?.moviePoster, object : Callback {
                    override fun onSuccess() {}
                    override fun onError(e: Exception?) {}
                })
        }else{
            binding?.moviePoster?.setImageDrawable(resources.getDrawable(R.drawable.posterplaceholdermovie))
        }

        binding?.tvTitleMovie?.text = latestResponse.originalTitle
        binding?.tvDescMovie?.text = latestResponse.overview
    }

    override fun onStop() {
        super.onStop()
        uiJob?.cancelChildren()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}