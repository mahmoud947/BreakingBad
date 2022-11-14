package com.example.breakingbad.peresentation.activity.splashScreen


import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.breakingbad.R
import com.example.breakingbad.databinding.ActivitySplashScreenBinding
import com.example.breakingbad.peresentation.activity.mainView.MainViewActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashScreenActivity : AppCompatActivity() {
    private val viewModel: SplashScreenViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivitySplashScreenBinding.inflate(layoutInflater)

        setContentView(binding.root)
        setUpWindow()
        lifecycleScope.launchWhenStarted {
            viewModel.event.collect {event->
                when(event){
                    is SplashScreenViewModel.SplashScreenEvent.Navigate->{
                        navigate()
                    }
                }
            }
        }

        binding.ivLogoWalter.apply {
            val anim =
                AnimationUtils.loadAnimation(this@SplashScreenActivity, R.anim.logo_top_animation)
            animation = anim
        }
        binding.ivLogoText.apply {
            val anim = AnimationUtils.loadAnimation(
                this@SplashScreenActivity,
                R.anim.logo_bottom_animation
            )
            animation = anim
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