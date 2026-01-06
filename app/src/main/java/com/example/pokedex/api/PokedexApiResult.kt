package com.example.pokedex.api

import java.io.Serializable

data class PokemonResult(
    val id: Int,
    val image: String?,
    val name: String,
    val type: String,
    val type2: String
) : Serializable
