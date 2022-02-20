package com.alextena.footballers.network

import com.alextena.footballers.model.ClubResponse
import com.alextena.footballers.model.PlayersResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface FootballApi {
    @GET("players")
    suspend fun getAllPlayers(
        @Query("page")
        pageNumber: Int = 1,
        @Query("limit")
        limit: Int = 20
    ) : Response <PlayersResponse>

    @GET("players/{player}/image")
    suspend fun getPlayerImage(
        @Path("player")
        playerId: Int = -1
    ) : Response <String>

    @GET("clubs/{id}")
    suspend fun getClubById(
        @Path("id")
        clubId: Int = -1
    ) : Response <ClubResponse>

    @GET("clubs/{id}/image")
    suspend fun getClubImage(
        @Path("id")
        clubId: Int = -1
    ) : Response <String>
}