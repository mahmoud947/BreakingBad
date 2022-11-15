package com.example.breakingbad.characters_feature.peresentation.activity.splashScreen


import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.example.breakingbad.R
import com.example.breakingbad.databinding.ActivitySplashScreenBinding
import com.example.breakingbad.characters_feature.peresentation.activity.mainView.MainViewActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashScreenBinding
    private val viewModel: SplashScreenViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash_screen)

        val imageAnim =
            AnimationUtils.loadAnimation(this@SplashScreenActivity, R.anim.logo_top_animation)
        val logoAnim = AnimationUtils.loadAnimation(
            this@SplashScreenActivity,
            R.anim.logo_bottom_animation
        )

        binding.lifecycleOwner = this
        binding.imageAnimation = imageAnim
        binding.logoAnimation = logoAnim
        setUpWindow()
        lifecycleScope.launchWhenStarted {
            viewModel.event.collect { event ->
                when (event) {
                    is SplashScreenViewModel.SplashScreenEvent.Navigate -> {
                        navigate()
                    }
                }
            }
        }


    }

    private fun setUpWindow() {
        window.statusBarColor = android.graphics.Color.TRANSPARENT
    }

    private fun navigate() {
        val intent = Intent(this, MainViewActivity::class.java)
        startActivity(intent)
        finish()
    }
}