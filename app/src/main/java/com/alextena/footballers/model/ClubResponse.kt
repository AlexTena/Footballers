package com.alextena.footballers.model

data class ClubResponse(val item: Item)

data class Item(val id: Int,
                val league: League,
                val name: String)

data class League(val id: Int,
                  val name: String)