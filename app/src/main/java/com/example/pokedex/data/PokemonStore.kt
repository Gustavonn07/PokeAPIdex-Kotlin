package com.example.pokedex.data

import com.example.pokedex.api.PokemonResult

object PokemonStore {

    private const val MAX = 6
    private val pokemons = mutableListOf<PokemonResult>()

    fun getAll(): List<PokemonResult> = pokemons

    fun add(pokemon: PokemonResult): Boolean {
        if (pokemons.size >= MAX) return false
        if (pokemons.any { it.id == pokemon.id }) return false
        pokemons.add(pokemon)
        return true
    }

    fun remove(pokemon: PokemonResult) {
        pokemons.removeIf { it.id == pokemon.id }
    }
}
