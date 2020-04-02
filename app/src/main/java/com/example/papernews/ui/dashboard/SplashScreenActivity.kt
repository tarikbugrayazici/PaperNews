package com.example.papernews.ui.dashboard

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.papernews.R

class SplashScreenActivity : AppCompatActivity() {
    private val SPLASH_TIME_OUT: Long = 3000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val preferences = getSharedPreferences("login", MODE_PRIVATE)
        val isLogged =
            preferences?.getBoolean("isLogged", false)

        Handler().postDelayed({
            if (isLogged!!) {
                val intent = Intent(this@SplashScreenActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                val intent = Intent(this@SplashScreenActivity, AuthenticationActivity::class.java)
                startActivity(intent)
                finish()
            }
        }, SPLASH_TIME_OUT)
    }
}
