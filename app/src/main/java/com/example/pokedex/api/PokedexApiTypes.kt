package com.example.pokedex.api

import kotlinx.serialization.Serializable

@Serializable
data class PokemonResponse(
    val id: Int,
    val name: String,
    val sprites: Sprites,
    val types: List<PokemonType>
)

@Serializable
data class Sprites(
    val front_default: String?
)

@Serializable
data class PokemonType(
    val type: TypeInfo
)

@Serializable
data class TypeInfo(
    val name: String
)

data class PokemonResult(
    val id: Int,
    val image: String?,
    val name: String,
    val type: String,
    val type2: String
)

