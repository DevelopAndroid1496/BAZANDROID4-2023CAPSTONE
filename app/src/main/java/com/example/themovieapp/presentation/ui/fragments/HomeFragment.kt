package com.example.themovieapp.presentation.ui.fragments

import android.app.AlertDialog
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.activity.addCallback
import androidx.annotation.RequiresApi
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.whenStarted
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.room.Database
import com.example.themovieapp.R
import com.example.themovieapp.common.DataState
import com.example.themovieapp.common.LoadingDialog
import com.example.themovieapp.data.model.genders.GenderMovie
import com.example.themovieapp.data.model.now.MovieRes
import com.example.themovieapp.databinding.FragmentHomeBinding
import com.example.themovieapp.domain.model.GenderDom
import com.example.themovieapp.domain.model.Movie
import com.example.themovieapp.presentation.ui.adapter.NowMoviesAdapter
import com.example.themovieapp.presentation.viewModel.MovieViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.launch

@Suppress("DEPRECATION")
@AndroidEntryPoint
class HomeFragment : Fragment(),View.OnClickListener,NowMoviesAdapter.OnItemClickListener{

    private var _binding : FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var googleSignInClient: GoogleSignInClient
    private var movieAdapter : NowMoviesAdapter? = null
    private val viewModel : MovieViewModel by viewModels()
    private var navBar : BottomNavigationView? = null
    private var uiJob: Job? = null
    private var uiJobGenders: Job? = null
    private var loadingDialog: LoadingDialog? = null
    private var listMovie: List<Movie> = emptyList()
    private var listNameGenres: ArrayList<String> = arrayListOf()

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getMoviesNow()
        viewModel.getGendersMovies()
        val callback = requireActivity().onBackPressedDispatcher.addCallback(this) {
            Toast.makeText(requireContext(),"PULSASTE ATRAS",Toast.LENGTH_LONG).show()

        }
    }

    override fun onStart() {
        super.onStart()
        getMovies()
        getGenders()
        getGendersFromDb()
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
        navBar = requireActivity().findViewById<BottomNavigationView>(R.id.bottom_nav_view)
        navBar?.visibility = View.VISIBLE
    }

    private fun getGenders(){
        uiJobGenders = lifecycleScope.launch {
            whenStarted {
                viewModel.movieGenders.collect{ gendersResponse ->
                    when(gendersResponse){
                        is DataState.Loading -> {}
                        is DataState.Success -> {}
                        is DataState.Error -> {}

                        else -> {}
                    }
                }
            }
        }
    }

    private fun getGendersFromDb(){
        lifecycleScope.launch {
            whenStarted {
                viewModel.gendersFromDB.collect{ generosName ->
                    when(generosName){
                        is DataState.Success -> {
                            //listNameGenres.add(generosName.data.name)
                            Toast.makeText(requireContext(),"Generos => "+generosName.data.name,Toast.LENGTH_SHORT).show()
                        }
                        else -> {}
                    }
                }
            }
        }
    }


    private fun getMovies(){
        uiJob = lifecycleScope.launch{
            whenStarted {
                viewModel.moviesNow.collect{ responseMovie ->
                    when(responseMovie){

                        is DataState.Loading -> {  loadingDialog?.startLoadingDialog() }
                        is DataState.Success -> {
                            loadingDialog?.dismisDialog()
                            listMovie = responseMovie.data
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
                movieAdapter = NowMoviesAdapter(ctx, listMovieRes,this@HomeFragment)
                adapter = movieAdapter
            }
        }
    }

    override fun onStop() {
        super.onStop()
        uiJob?.cancelChildren()
        uiJobGenders?.cancelChildren()
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

    override fun onClickItem(position: Int,movie: Movie) {
        listMovie[position].gender.forEach { idGender -> viewModel.collectIdBygetGenders(idGender) }

        val bundle = Bundle().apply {
            putParcelable("movie",movie)
            putStringArrayList("NAMES_GENDERS",listNameGenres)
        }
        //val bundlle = bundleOf("movie" to movie)
        findNavController().navigate(R.id.detailMovieFragment,bundle)
    }

}