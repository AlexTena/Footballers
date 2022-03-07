package com.alextena.footballers.model

data class PlayersResponse(
    val count: Int,
    val count_total: Int,
    val items: MutableList<Player>,
    val items_per_page: Int,
    val page: Int,
    val page_total: Int
)