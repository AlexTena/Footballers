package com.alextena.footballers.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.alextena.footballers.databinding.ItemPlayerCardBinding
import com.alextena.footballers.model.Player
import com.bumptech.glide.Glide

class FootballersAdapter : RecyclerView.Adapter<FootballersAdapter.PlayerViewHolder>() {

    inner class PlayerViewHolder(val binding: ItemPlayerCardBinding): RecyclerView.ViewHolder(binding.root)

    private val differCallback = object : DiffUtil.ItemCallback<Player>() {
        override fun areItemsTheSame(oldItem: Player, newItem: Player): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Player, newItem: Player): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        val binding = ItemPlayerCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PlayerViewHolder(binding)
    }

    override fun getItemCount(): Int = differ.currentList.size

    private var onItemClickListener: ((Player) -> Unit)? = null

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        val player = differ.currentList[position]
        with(holder) {
            binding.apply {
                player.playerImage?.let { playerImage ->
                    Glide.with(itemView).asBitmap().load(playerImage).into(image)
                }
                name.text = player.common_name

                itemView.setOnClickListener {
                    onItemClickListener?.let { it(player) }
                }
            }
        }
    }

    fun setOnItemClickListener(listener: (Player) -> Unit) {
        onItemClickListener = listener
    }
}