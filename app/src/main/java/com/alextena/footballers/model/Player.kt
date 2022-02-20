package com.alextena.footballers.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(
    tableName = "players"
)
data class Player(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val age: Int,
    val attack_work_rate: String,
    val birth_date: String,
    val club: Int,
    val common_name: String,
    val defending: Int,
    val defense_work_rate: String,
    val dribbling: Int,
    val first_name: String,
    val foot: String,
    val fut_bin_id: Int,
    val fut_wiz_id: Int,
    val height: Int,
    val last_name: String,
    val league: Int,
    val name: String,
    val nation: Int,
    val pace: Int,
    val passing: Int,
    val physicality: Int,
    val position: String,
    val rarity: Int,
    val rating: Int,
    val rating_average: Int,
    val resource_base_id: Int,
    val resource_id: Int,
    val shooting: Int,
    val skill_moves: Int,
    val tradeable: Boolean,
    val weak_foot: Int,
    val weight: Int
) : Serializable