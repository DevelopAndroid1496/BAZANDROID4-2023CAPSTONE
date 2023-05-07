package com.example.themovieapp.presentation.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.whenStarted
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.themovieapp.common.DataState
import com.example.themovieapp.data.model.now.MovieRes
import com.example.themovieapp.databinding.FragmentTopRatedMovieBinding
import com.example.themovieapp.domain.model.Movie
import com.example.themovieapp.presentation.ui.adapter.TopMoviesAdapter
import com.example.themovieapp.presentation.viewModel.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TopRatedMovieFragment : Fragment() {

    private var _binding : FragmentTopRatedMovieBinding? = null
    private val binding get() = _binding
    private var movieTopAdapter : TopMoviesAdapter? = null
    private var uiJob : Job? = null
    private val viewModel : MovieViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getMovieTopRated()
    }

    override fun onStart() {
        super.onStart()
        getTopRatedMovies()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentTopRatedMovieBinding.inflate(inflater,container,false)
        viewModel.getMovieTopRated()
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun getTopRatedMovies(){
        uiJob = lifecycleScope.launch {
            whenStarted {
                viewModel.moviesTopRated.collect{ stateTopRatedMovie ->
                    when(stateTopRatedMovie){
                        is DataState.Loading -> {

                        }
                        is DataState.Success -> {
                            initRecycler(stateTopRatedMovie.data)
                        }
                        is DataState.Error -> {

                        }
                        else -> {}
                    }
                }
            }
        }
    }

    private fun initRecycler(listMovie:List<Movie>){
        binding?.topMoviesRecyclerView.apply {
            context?.let{ ctx ->
                this?.layoutManager = LinearLayoutManager(ctx, RecyclerView.VERTICAL, false)
                movieTopAdapter = TopMoviesAdapter(ctx, listMovie)
                this?.adapter = movieTopAdapter
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}