package com.example.airlineapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.airlineapp.R
import com.example.airlineapp.ui.home.HomeFragment
import kotlinx.android.synthetic.main.landing_screen.*

class LandingScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.landing_screen)
        init()
    }

    private fun init() {
        setUpCustomActionBar()
        loadHomeFragment()
    }

    private fun setUpCustomActionBar() {
        val toolbar = appToolbar as Toolbar
        setSupportActionBar(toolbar)
    }

    private fun loadHomeFragment() {
        supportFragmentManager
            .beginTransaction()
            .replace(fragment_container.id, HomeFragment())
            .commit()
    }
}
