package com.example.breakingbad.peresentation.activity.mainView

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.breakingbad.R
import com.example.breakingbad.databinding.ActivityMainViewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainViewActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding:ActivityMainViewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainViewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container)
        navController = Navigation.findNavController(this,R.id.nav_host_fragment_container)

        binding.bottomNavigationView.setupWithNavController(navController)
        setSupportActionBar(binding.toolbar)

        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.quotesFragment, R.id.charactersFragment, R.id.deathFragment,R.id.episodeFragment)
        )


        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    override fun onNavigateUp(): Boolean {
        return navController.navigateUp()||super.onNavigateUp()
    }




}