package com.alextena.footballers.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.alextena.footballers.R
import com.alextena.footballers.databinding.ActivityFootballersBinding
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationView

class FootballersActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityFootballersBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navController = this.findNavController(R.id.footballerNavHostFragment)
        binding.bottomNavigationView.setupWithNavController(navController)
    }

}