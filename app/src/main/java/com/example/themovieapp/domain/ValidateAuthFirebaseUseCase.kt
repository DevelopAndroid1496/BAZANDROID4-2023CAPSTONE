package com.example.themovieapp.domain

import android.content.Intent
import android.graphics.drawable.Drawable
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class ValidateAuthFirebaseUseCase @Inject constructor(
    private val auth: FirebaseAuth,
    private val uiFb: AuthUI
) {

    private fun isExistUser(): Boolean {
        return auth.currentUser == null
    }

    fun generateUILogin(iconAvatar : Int? = null): Intent? {
        return if (isExistUser())
            uiFb.createSignInIntentBuilder()
                .setLogo(android.R.drawable.btn_dropdown)
                .setAvailableProviders(
                    listOf(
                        AuthUI.IdpConfig.EmailBuilder().build(),
                        AuthUI.IdpConfig.GoogleBuilder().build(),
                    )
                ).build()
        else
            null

    }

}