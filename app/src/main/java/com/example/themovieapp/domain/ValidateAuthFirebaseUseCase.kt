package com.example.themovieapp.domain

import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import com.example.themovieapp.R
import com.firebase.ui.auth.AuthUI
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class ValidateAuthFirebaseUseCase @Inject constructor(
    private val auth: FirebaseAuth
) {

    private var googleSingInClient: GoogleSignInClient? = null

    fun isExistUser(): Boolean {
        return auth.currentUser == null
    }
    fun getInstanceAuthFb():FirebaseAuth{
        return auth
    }


    fun authGmailClient(ctx : Context) : GoogleSignInOptions{
        return GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(ctx.getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
    }

}