package com.alextena.footballers.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.alextena.footballers.R
import com.alextena.footballers.databinding.FragmentSavedPlayersBinding
import com.alextena.footballers.ui.adapters.FootballersAdapter
import com.alextena.footballers.ui.main.FootballersViewModel


class SavedPlayersFragment : Fragment() {
    private lateinit var binding: FragmentSavedPlayersBinding
    private lateinit var playersAdapter: FootballersAdapter
    private val sharedViewModel: FootballersViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        binding = FragmentSavedPlayersBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()

        playersAdapter.setOnItemClickListener { player ->
            player.isSavedPlayer = true

            val bundle = Bundle().apply {
                putSerializable("player", player)
            }
            findNavController().navigate(
                R.id.action_savedPlayersFragment_to_playerDetailsFragment,
                bundle
            )
        }

        sharedViewModel.getSavedPlayers().observe(viewLifecycleOwner, Observer { players ->
            playersAdapter.differ.submitList(players)
        })
    }

    private fun setupRecyclerView() {
        playersAdapter = FootballersAdapter()
        binding.recyclerView.apply {
            adapter = playersAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
}