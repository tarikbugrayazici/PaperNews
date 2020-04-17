package com.example.papernews.ui.dashboard

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.FragmentTransaction
import com.example.papernews.R
import com.example.papernews.ui.navigationFragments.view.FavoriteFragment
import com.example.papernews.ui.navigationFragments.view.SearchFragment
import com.example.papernews.ui.navigationFragments.view.SourceFragment
import com.facebook.stetho.Stetho
import com.firebase.ui.auth.AuthUI
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.nav_header.view.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    val sourceFragment =
        SourceFragment()
    val searchFragment =
        SearchFragment()
    val favoriteFragment =
        FavoriteFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Stetho.initializeWithDefaults(this)
//        var userPhoto = intent.getStringExtra("userPhoto")
//        var userName = intent.getStringExtra("userName")
//        var userEmail = intent.getStringExtra("userEmail")


        setDrawerLayout()
        setUserInfo()
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(p0: MenuItem): Boolean {
        when (p0.itemId) {
            R.id.navSource -> supportFragmentManager
                .beginTransaction()
                .replace(
                    R.id.frameContainer, sourceFragment
                )
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit()
            R.id.navSearch -> supportFragmentManager
                .beginTransaction()
                .replace(R.id.frameContainer, searchFragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit()
            R.id.navFavorite -> supportFragmentManager
                .beginTransaction()
                .replace(R.id.frameContainer, favoriteFragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit()
            R.id.navLogout ->
                logOut()
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun setDrawerLayout() {
        setSupportActionBar(toolbar)
        val toggle: ActionBarDrawerToggle = object : ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolbar,
            (R.string.navigation_drawer_open),
            (R.string.navigation_drawer_close)
        ) {}
        toggle.isDrawerIndicatorEnabled = true
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        navView.setNavigationItemSelectedListener(this)
        supportFragmentManager
            .beginTransaction()
            .replace(
                R.id.frameContainer, sourceFragment
            )
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()
    }

    private fun setUserInfo() {
        val preferences = getSharedPreferences("login", MODE_PRIVATE)
        val userName = preferences?.getString("userName", "")
        val userEmail = preferences?.getString("userEmail", "")
        navView.getHeaderView(0).navUserName.text = userName
        /*Glide.with(this@MainActivity)
            .load(userPhoto)
            .centerCrop()
            .into(navView.getHeaderView(0).navUserPhoto)*/
        navView.getHeaderView(0).navUserMail.text = userEmail

    }

    private fun logOut() {
        var isLoggedIn: Boolean = false
        val preferences = getSharedPreferences("login", MODE_PRIVATE)
        val editor = preferences.edit()
        editor?.putBoolean("isLogged", isLoggedIn)
        editor.apply()
        AuthUI.getInstance().signOut(this@MainActivity)
            .addOnCompleteListener {
                val intent = Intent(
                    this@MainActivity,
                    AuthenticationActivity::class.java
                )
                startActivity(intent)
            }
    }
}
