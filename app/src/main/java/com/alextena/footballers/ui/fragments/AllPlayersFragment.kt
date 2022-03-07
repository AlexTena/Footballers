package com.alextena.footballers.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.alextena.footballers.R
import com.alextena.footballers.databinding.FragmentAllPlayersBinding
import com.alextena.footballers.ui.adapters.FootballersAdapter
import com.alextena.footballers.ui.main.FootballersViewModel
import com.alextena.footballers.utils.Constants.Companion.QUERY_PAGE_SIZE
import com.alextena.footballers.utils.ExtensionUtils.hide
import com.alextena.footballers.utils.ExtensionUtils.show
import com.alextena.footballers.utils.PaginationScrollListener
import com.alextena.footballers.utils.Resource


class AllPlayersFragment : Fragment() {
    private lateinit var binding: FragmentAllPlayersBinding
    private lateinit var playersAdapter: FootballersAdapter
    private val sharedViewModel: FootballersViewModel by activityViewModels()
    private val TAG = "AllPlayersFragment"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        binding = FragmentAllPlayersBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()

        playersAdapter.setOnItemClickListener { player ->
            player.isSavedPlayer = false

            val bundle = Bundle().apply {
                putSerializable("player", player)
            }
            findNavController().navigate(
                R.id.action_allPlayersFragment_to_playerDetailsFragment,
                bundle
            )
        }

        sharedViewModel.players.observe(viewLifecycleOwner, Observer { response ->
            when(response) {
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let { playersResponse ->
                        playersAdapter.differ.submitList(playersResponse.items.toList())
                        val totalPages = playersResponse.page_total
                        isLastPage = sharedViewModel.footballersPage == totalPages
                    }
                }
                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let { message ->
                        Log.e(TAG,"Error fetching players data: $message")
                    }
                }
                is Resource.Loading -> {
                    showProgressBar()
                }
            }
        })
    }

    private fun hideProgressBar() {
        binding.progressbar.hide()
        isLoading = false
    }
    private fun showProgressBar() {
        binding.progressbar.show()
        isLoading = true
    }

    var isLoading: Boolean = false
    var isLastPage: Boolean = false

    private fun setupRecyclerView() {
        with(binding) {
            playersAdapter = FootballersAdapter()
            recyclerView.layoutManager = GridLayoutManager(activity, 2)

            val scrollListener = object : PaginationScrollListener(recyclerView.layoutManager as GridLayoutManager) {
                override fun isLastPage(): Boolean {
                    return isLastPage
                }

                override fun isLoading(): Boolean {
                    return isLoading
                }

                override fun loadMoreItems() {
                    sharedViewModel.getAllPlayers(pageNumber = sharedViewModel.footballersPage, limit = QUERY_PAGE_SIZE)
                }
            }
            recyclerView.apply {
                adapter = playersAdapter
                addOnScrollListener(scrollListener)
            }
        }
    }

}