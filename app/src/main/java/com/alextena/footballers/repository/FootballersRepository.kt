package com.alextena.footballers.repository

import com.alextena.footballers.model.Player
import com.alextena.footballers.network.RetrofitInstance
import com.alextena.footballers.persistence.AppDatabase

class FootballersRepository(
    val db: AppDatabase
) {
    //Retrofit
    suspend fun getAllPlayers(pageNumber: Int, limit: Int) =
        RetrofitInstance.api.getAllPlayers(pageNumber, limit)

    suspend fun getPlayerImage(playerId: Int) =
        RetrofitInstance.api.getPlayerImage(playerId)

    suspend fun getClubById(clubId: Int) =
        RetrofitInstance.api.getClubById(clubId)

    suspend fun getClubImage(clubId: Int) =
        RetrofitInstance.api.getClubImage(clubId)


    //Room
    suspend fun upsert(player: Player) = db.getFootballerDao().upsert(player)

    fun getAllPlayers() = db.getFootballerDao().getAllPlayers()

    suspend fun deletePlayer(player: Player) = db.getFootballerDao().deletePlayer(player)
}