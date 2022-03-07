package com.alextena.footballers.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.alextena.footballers.R
import com.alextena.footballers.databinding.ActivityFootballersBinding
import com.alextena.footballers.persistence.AppDatabase
import com.alextena.footballers.repository.FootballersRepository

class FootballersActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFootballersBinding
    private val sharedViewModel: FootballersViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFootballersBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val footballersRepository = FootballersRepository(AppDatabase(this))
        sharedViewModel.loadData(footballersRepository)
    }

    override fun onStart() {
        super.onStart()
        val navController = this.findNavController(R.id.footballerNavHostFragment)
        binding.bottomNavigationView.setupWithNavController(navController)
    }

}