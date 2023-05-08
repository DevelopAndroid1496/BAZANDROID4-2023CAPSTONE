package com.example.themovieapp.presentation.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ViewCompositionStrategy
import com.example.themovieapp.R
import com.example.themovieapp.databinding.FragmentDetailMovieBinding
import com.example.themovieapp.domain.model.Movie
import com.example.themovieapp.presentation.ui.fragments.compose.MovieDetailScreen
import com.google.android.material.bottomnavigation.BottomNavigationView

class DetailMovieFragment : Fragment() {

    private var _binding: FragmentDetailMovieBinding? = null
    private val binding get() = _binding!!
    private var movie: Movie? = null
    private var navBar : BottomNavigationView? = null
    private var listGenres: ArrayList<String> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentDetailMovieBinding.inflate(inflater,container,false)
        binding.composeView.apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                MaterialTheme {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                    ) {
                        //MovieDetailScreen(path = "https://www.nationalgeographic.com.es/medio/2022/12/12/perro-1_514aad3b_221212161023_1280x720.jpg")
                        movie?.backdropPath?.let { MovieDetailScreen(it, movie!!.title,movie!!.overview,listGenres) }
                    }
                }
            }
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navBar = requireActivity().findViewById<BottomNavigationView>(R.id.bottom_nav_view)
        navBar?.visibility = View.GONE
        arguments?.let {
            movie = it.getParcelable("movie")
            listGenres = it.getStringArrayList("NAMES_GENDERS") as ArrayList<String>
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}