package com.alextena.footballers.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.alextena.footballers.model.Footballer

class PlayersAdapter (var actions: PlayersAdapterActions)
    : ListAdapter<Footballer, PlayersAdapter.ViewHolder>(FootballerDifferCallback()){

    private class FootballerDifferCallback : DiffUtil.ItemCallback<Footballer>(){
        override fun areItemsTheSame(oldItem: Footballer, newItem: Footballer): Boolean {
            TODO("Not yet implemented")
        }

        override fun areContentsTheSame(oldItem: Footballer, newItem: Footballer): Boolean {
            TODO("Not yet implemented")
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayersAdapter.ViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: PlayersAdapter.ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    inner class ViewHolder(): RecyclerView.ViewHolder() {

    }

    interface PlayersAdapterActions {
        fun onSelected()
    }
}