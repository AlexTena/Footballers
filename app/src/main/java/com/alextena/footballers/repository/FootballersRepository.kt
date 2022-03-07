package com.alextena.footballers.repository

import com.alextena.footballers.model.Player
import com.alextena.footballers.network.RetrofitInstance
import com.alextena.footballers.persistence.AppDatabase

class FootballersRepository(
    private val db: AppDatabase
) {
    //Retrofit
    suspend fun getAllPlayers(pageNumber: Int, limit: Int) =
        RetrofitInstance.api.getAllPlayers(pageNumber, limit)

    suspend fun getPlayerImage(playerId: Int) =
        RetrofitInstance.api.getPlayerImage(playerId)

    //Room
    suspend fun upsert(player: Player) = db.getFootballerDao().upsert(player)

    fun getSavedPlayers() = db.getFootballerDao().getSavedPlayers()

    suspend fun deletePlayer(player: Player) = db.getFootballerDao().deletePlayer(player)
}