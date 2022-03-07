package com.alextena.footballers.ui.fragments.detailsFgm

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.alextena.footballers.R
import com.alextena.footballers.databinding.FragmentPlayerDetailsBinding
import com.alextena.footballers.model.Player
import com.alextena.footballers.ui.main.FootballersViewModel
import com.alextena.footballers.utils.ExtensionUtils.hide
import com.alextena.footballers.utils.ExtensionUtils.show
import com.alextena.footballers.utils.ExtensionUtils.snackbar
import com.alextena.footballers.utils.Resource
import com.bumptech.glide.Glide


class PlayerDetailsFragment : Fragment() {
    private lateinit var binding: FragmentPlayerDetailsBinding
    private val sharedViewModel: FootballersViewModel by activityViewModels()
    private val viewModel: DetailsViewModel by viewModels()
    private val args: PlayerDetailsFragmentArgs by navArgs()
    private val TAG = "PlayerDetailsFragment"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        binding = FragmentPlayerDetailsBinding.inflate(layoutInflater)
        setupLayout()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val player = args.player

        binding.arrow.setOnClickListener {
            activity?.onBackPressed()
        }

        viewModel.clubImage.observe(viewLifecycleOwner, Observer { response ->
            when(response) {
                is Resource.Success -> {
                    binding.progressbar.hide()
                    response.data?.let { imageResponse ->
                        Glide.with(this).asBitmap().load(imageResponse).into(binding.clubImage)
                    }
                }
                is Resource.Error -> {
                    binding.progressbar.hide()
                    response.message?.let { message ->
                        Log.e(TAG,"Error fetching club image data: $message")
                    }
                }
                is Resource.Loading -> {
                    binding.progressbar.show()
                }
            }
        })

        viewModel.clubName.observe(viewLifecycleOwner, Observer { response ->
            when(response) {
                is Resource.Success -> {
                    response.data?.let { clubResponse ->
                        binding.clubName.text = clubResponse.item.name
                    }
                }
                is Resource.Error -> {
                    response.message?.let { message ->
                        Log.e(TAG,"Error fetching club name data: $message")
                    }
                }
                is Resource.Loading -> {
                    binding.progressbar.show()
                }
            }
        })
        viewModel.getClubImage(player.club)
        viewModel.getClubName(player.club)
    }

    private fun setupLayout() {
        val player = args.player
        val context = this
        binding.apply {
            Glide.with(context).asBitmap().load(player.playerImage).into(image)
            name.text = player.common_name
            position.text = player.position
            progressPace.progress = player.pace.toFloat()
            progressPace.labelText = "${player.pace}/99"

            progressDribbling.progress = player.dribbling.toFloat()
            progressDribbling.labelText = "${player.dribbling}/99"

            progressPassing.progress = player.passing.toFloat()
            progressPassing.labelText = "${player.passing}/99"

            progressShooting.progress = player.shooting.toFloat()
            progressShooting.labelText = "${player.shooting}/99"

            progressDefence.progress = player.defending.toFloat()
            progressDefence.labelText = "${player.defending}/99"

            setupFloatingActionButton(player)
        }
    }

    private fun setupFloatingActionButton(player: Player) {
        binding.apply {
            if(player.isSavedPlayer) {
                fab.setImageResource(R.drawable.ic_baseline_delete_24)
                fab.setOnClickListener {
                    sharedViewModel.deletePlayer(player)
                    root.snackbar(getString(R.string.player_deleted))
                    activity?.onBackPressed()
                }
            }
            else {
                fab.setImageResource(R.drawable.ic_favorite)
                fab.setOnClickListener {
                    sharedViewModel.savePlayer(player)
                    root.snackbar(getString(R.string.player_saved))
                }
            }
        }
    }

}