package com.example.themovieapp.presentation.ui.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.whenStarted
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.themovieapp.R
import com.example.themovieapp.common.DataState
import com.example.themovieapp.common.LoadingDialog
import com.example.themovieapp.data.model.now.MovieRes
import com.example.themovieapp.databinding.FragmentHomeBinding
import com.example.themovieapp.domain.model.Movie
import com.example.themovieapp.presentation.ui.adapter.NowMoviesAdapter
import com.example.themovieapp.presentation.viewModel.MovieViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.launch

@Suppress("DEPRECATION")
@AndroidEntryPoint
class HomeFragment : Fragment(),View.OnClickListener {

    private var _binding : FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var googleSignInClient: GoogleSignInClient
    private var movieAdapter : NowMoviesAdapter? = null
    private val viewModel : MovieViewModel by viewModels()
    private var listMovies: List<Movie>? = null
    private var uiJob: Job? = null
    private var loadingDialog: LoadingDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getMoviesNow()
    }

    override fun onStart() {
        super.onStart()
        getMovies()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.fabLogOut.setOnClickListener(this)
        googleSignInClient = GoogleSignIn.getClient(requireActivity(), GoogleSignInOptions.DEFAULT_SIGN_IN)
        loadingDialog = LoadingDialog(requireActivity())
    }

    private fun getMovies(){
        /*listMovies = viewModel.getMoviesNow()
        initRecycler(listMovieRes = listMovies!!)*/
        uiJob = lifecycleScope.launch{
            whenStarted {
                viewModel.moviesNow.collect{ responseMovie ->
                    when(responseMovie){

                        is DataState.Loading -> {  loadingDialog?.startLoadingDialog() }
                        is DataState.Success -> {
                            loadingDialog?.dismisDialog()
                            initRecycler(responseMovie.data)
                        }
                        is DataState.Error -> { loadingDialog?.dismisDialog() }

                        else -> {}
                    }
                }
            }
        }
    }

    private fun initRecycler(listMovieRes:List<Movie>){
        binding.postsRecyclerView.apply {
            context?.let{ ctx ->
                layoutManager = GridLayoutManager(ctx,2)
                movieAdapter = NowMoviesAdapter(ctx, listMovieRes)
                adapter = movieAdapter
            }
        }
    }

    override fun onStop() {
        super.onStop()
        uiJob?.cancelChildren()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onClick(v: View?) {
        when(v?.id){
            binding.fabLogOut.id -> {
                googleSignInClient.signOut().addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        viewModel.getInstanceAuthFb().signOut()
                        findNavController().navigate(R.id.loginFragment)
                    }
                }
            }
        }
    }

}