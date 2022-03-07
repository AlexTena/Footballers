package com.alextena.footballers.repository

import com.alextena.footballers.network.RetrofitInstance

class DetailsRepository {
    suspend fun getClubById(clubId: Int) =
        RetrofitInstance.api.getClubById(clubId)

    suspend fun getClubImage(clubId: Int) =
        RetrofitInstance.api.getClubImage(clubId)
}