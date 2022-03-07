package com.alextena.footballers.persistence

import androidx.lifecycle.LiveData
import androidx.room.*
import com.alextena.footballers.model.Player

@Dao
interface FootballerDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(player: Player): Long

    @Query("SELECT * FROM players")
    fun getSavedPlayers(): LiveData<List<Player>>

    @Delete
    suspend fun deletePlayer(player: Player)
}