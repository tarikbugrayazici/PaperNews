package com.example.papernews.ui.dashboard


import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast
import com.example.papernews.R
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser


class AuthenticationActivity : AppCompatActivity() {

    private val RC_SIGN_IN: Int = 1
    lateinit var providers: ArrayList<AuthUI.IdpConfig>
    private var user: FirebaseUser? = null
    private var isLoggedIn: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authentication)
        providers = arrayListOf(
            AuthUI.IdpConfig.EmailBuilder().build(),
            AuthUI.IdpConfig.PhoneBuilder().build(),
            AuthUI.IdpConfig.GoogleBuilder().build(),
            AuthUI.IdpConfig.TwitterBuilder().build()
        )
        showSignInOptions()
        /* logOut.setOnClickListener {
             AuthUI.getInstance().signOut(this@AuthenticationActivity)
                 .addOnCompleteListener {
                     logOut.isEnabled = false
                     showSignInOptions()
                 }
                 .addOnFailureListener { e ->
                     Toast.makeText(this@AuthenticationActivity, e.message, Toast.LENGTH_LONG).show()
                 }
         }*/
    }

    private fun showSignInOptions() {
        startActivityForResult(
            AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .build(),
            RC_SIGN_IN
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val response = IdpResponse.fromResultIntent(data)
            if (resultCode == Activity.RESULT_OK) {
                // Successfully signed in
                isLoggedIn = true
                val preferences = this@AuthenticationActivity.getSharedPreferences(
                    "login",
                    Context.MODE_PRIVATE
                )
                val editor = preferences.edit()

                user = FirebaseAuth.getInstance().currentUser
                editor?.putBoolean("isLogged", isLoggedIn)
                editor?.putString("userName", user?.displayName)
                editor?.putString("userEmail", user?.email)
                editor?.apply()
                Toast.makeText(
                    this,
                    "Hoşgeldiniz " + user!!.email + user!!.displayName,
                    Toast.LENGTH_LONG
                ).show()
                updateUI()
                // ...w
            } else {
                //updateUI()
                // Sign in failed. If response is null the user canceled the
                // sign-in flow using the back button. Otherwise check
                // ...
            }
        }
    }

    private fun updateUI() {
        Toast.makeText(this@AuthenticationActivity, "You're logged in", Toast.LENGTH_LONG)
        val intent = Intent(this@AuthenticationActivity, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}