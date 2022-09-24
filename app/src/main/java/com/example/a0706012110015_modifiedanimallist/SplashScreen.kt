package com.example.a0706012110015_modifiedanimallist

import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.example.a0706012110015_modifiedanimallist.databinding.ActivitySplashScreenBinding


class SplashScreen : AppCompatActivity() {
    private lateinit var bind : ActivitySplashScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(bind.root)
        val act_Bar = supportActionBar
        act_Bar?.hide()

        val handler = Handler()
        handler.postDelayed(Runnable {
            val abc = Intent(this@SplashScreen, MainActivity::class.java)
            startActivity(abc)
            finish()
        }, 3000)
    }
}