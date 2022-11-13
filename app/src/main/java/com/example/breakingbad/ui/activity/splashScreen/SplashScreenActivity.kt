package com.example.breakingbad.ui.activity.splashScreen

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.WindowManager
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.graphics.Color
import androidx.core.os.HandlerCompat.postDelayed
import com.example.breakingbad.R
import com.example.breakingbad.databinding.ActivitySplashScreenBinding
import com.example.breakingbad.ui.activity.mainView.MainViewActivity
import com.example.breakingbad.util.Constant.Companion.SPLASH_SCREEN_TIME_OUT
import dagger.hilt.android.AndroidEntryPoint

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpWindow()
        binding.ivLogoWalter.apply {
            val anim =
                AnimationUtils.loadAnimation(this@SplashScreenActivity, R.anim.logo_top_animation)
            animation = anim
        }
        binding.ivLogoText.apply {
            val anim = AnimationUtils.loadAnimation(this@SplashScreenActivity,
                R.anim.logo_bottom_animation)
            animation = anim
        }
        finishScreen()
    }

    private fun setUpWindow() {
        window.statusBarColor = android.graphics.Color.TRANSPARENT
    }
    private fun finishScreen(){
        Handler().postDelayed({
            val intent=Intent(this,MainViewActivity::class.java)
            startActivity(intent)
            finish()
        },SPLASH_SCREEN_TIME_OUT.toLong()
        )
    }


}