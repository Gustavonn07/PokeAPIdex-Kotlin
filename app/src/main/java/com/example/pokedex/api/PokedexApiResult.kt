package com.example.pokedex.api

data class PokemonResult(
    val id: Int,
    val name: String,
    val image: String?,
    val type: String,
    val type2: String
)
