package com.example.themovieapp.presentation.ui.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.themovieapp.R
import com.example.themovieapp.databinding.FragmentLoginBinding
import com.example.themovieapp.presentation.viewModel.MovieViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.GoogleAuthProvider
import dagger.hilt.android.AndroidEntryPoint

@Suppress("UNREACHABLE_CODE", "DEPRECATION")
@AndroidEntryPoint
class LoginFragment : Fragment(), View.OnClickListener{


    private var _binding : FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private var googleSignInClient: GoogleSignInClient? = null
    private val viewModel: MovieViewModel by viewModels()
    private var navBar : BottomNavigationView? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initEvents()
        initArgumentsAndUI()
    }

    private fun initEvents(){
        binding.btnLogin.setOnClickListener(this)
        binding.tvSingUp.setOnClickListener(this)
        binding.btSignIn.setOnClickListener(this)
    }

    private fun initArgumentsAndUI(){
        navBar = requireActivity().findViewById<BottomNavigationView>(R.id.bottom_nav_view)
        if (!viewModel.checkUserFirebase()){
            findNavController().navigate(R.id.homeFragment)
            navBar?.visibility = View.VISIBLE
        } else {
            null
        }

    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 100){
           val singInAccountTask : Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            if (singInAccountTask.isSuccessful){
                try {
                    val googleSignInAccount : GoogleSignInAccount = singInAccountTask.result
                    val authCredential = GoogleAuthProvider.getCredential(googleSignInAccount.idToken,null)
                    viewModel.getInstanceAuthFb().signInWithCredential(authCredential).addOnCompleteListener {
                        if (it.isSuccessful){
                            findNavController().navigate(R.id.homeFragment)
                        }else{
                            Toast.makeText(requireContext(),"Error Auth",Toast.LENGTH_LONG).show()
                        }
                    }
                }catch (e: ApiException){
                    e.printStackTrace()
                }
            }
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onClick(v: View?) {
        when(v?.id){
            binding.btnLogin.id -> {

                if (!binding.tieUserLogin.text.isNullOrEmpty() && !binding.tiePasswordLogin.text.isNullOrEmpty()) {
                    viewModel.getInstanceAuthFb().signInWithEmailAndPassword(
                        binding.tieUserLogin.text.toString(),
                        binding.tiePasswordLogin.text.toString()
                    )
                        .addOnCompleteListener(requireActivity()) { task ->
                            if (task.isSuccessful) {
                                findNavController().navigate(R.id.homeFragment)
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
                }else{
                    Toast.makeText(requireContext(),"Ingrese datos!",Toast.LENGTH_LONG).show()
                }


            }
            binding.btSignIn.id -> {
                val googleOptions = viewModel.getGoogleSignInOptionByAuth(requireContext())
                googleSignInClient = GoogleSignIn.getClient(requireActivity(),googleOptions)
                val intent = googleSignInClient?.signInIntent
                startActivityForResult(intent,100)
            }

            binding.tvSingUp.id ->{
                findNavController().navigate(R.id.action_loginFragment_to_signUpFragment)
            }
        }
    }

}