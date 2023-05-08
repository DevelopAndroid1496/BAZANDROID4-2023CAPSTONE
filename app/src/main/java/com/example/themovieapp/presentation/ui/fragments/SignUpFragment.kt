package com.example.themovieapp.presentation.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.themovieapp.R
import com.example.themovieapp.databinding.FragmentSignUpBinding
import com.example.themovieapp.presentation.viewModel.MovieViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpFragment : Fragment(), View.OnClickListener {

    private var _binding: FragmentSignUpBinding? = null
    private val binding get() = _binding
    private var navBar : BottomNavigationView? = null
    private val viewModel : MovieViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSignUpBinding.inflate(inflater,container,false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.btnSignUp?.setOnClickListener(this)
        navBar = requireActivity().findViewById(R.id.bottom_nav_view)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onClick(v: View?) {
        when(v?.id){
            binding?.btnSignUp?.id -> {

                if (!binding?.tieUserLogin?.text.isNullOrEmpty() && !binding?.tiePasswordLogin?.text.isNullOrEmpty()) {
                    viewModel.getInstanceAuthFb().createUserWithEmailAndPassword(
                        binding?.tieUserLogin?.text.toString(),
                        binding?.tiePasswordLogin?.text.toString()
                    )
                        .addOnCompleteListener(requireActivity()) { task ->
                            if (task.isSuccessful) {
                                findNavController().navigate(R.id.loginFragment)
                            } else {
                                when (task.exception) {
                                    is FirebaseAuthInvalidCredentialsException -> {
                                        Toast.makeText(
                                            requireActivity(),
                                            "Correo Invalido!",
                                            Toast.LENGTH_LONG
                                        ).show()
                                    }
                                }
                            }
                        }
                } else {
                    Toast.makeText(requireContext(), "Ingrese datos!", Toast.LENGTH_LONG).show()
                }

            }

        }
    }

}