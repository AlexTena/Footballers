package com.alextena.footballers.model

import android.graphics.Bitmap
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
    val birth_date: String,
    val club: Int,
    val common_name: String,
    val defending: Int,
    val dribbling: Int,
    val foot: String,
    val height: Int,
    val league: Int,
    val name: String,
    val nation: Int,
    val pace: Int,
    val passing: Int,
    val physicality: Int,
    val position: String,
    val rating_average: Int,
    val shooting: Int,
    val weak_foot: Int,
    val weight: Int,
    var playerImage: Bitmap?,
    var isSavedPlayer: Boolean = false
) : Serializable