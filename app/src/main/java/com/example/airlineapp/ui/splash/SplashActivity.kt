package com.example.airlineapp.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.airlineapp.R
import com.example.airlineapp.ui.LandingScreenActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        moveToLandingScreenAfterElapsedTime()
    }

    private fun moveToLandingScreenAfterElapsedTime() {
        Handler().postDelayed({
            val intent = Intent(this@SplashActivity, LandingScreenActivity::class.java)
            startActivity(intent)
            finish()
        }, TIME_OUT)
    }

    companion object {
        const val TIME_OUT: Long = 3000
    }
}
