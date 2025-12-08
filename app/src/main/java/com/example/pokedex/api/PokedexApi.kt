package com.example.pokedex.api

import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL

class PokemonApi {

    private fun fetch(urlStr: String): String {
        val url = URL(urlStr)
        val connection = url.openConnection() as HttpURLConnection

        return connection.run {
            requestMethod = "GET"
            inputStream.bufferedReader().use { it.readText() }
        }
    }

    suspend fun generatePokemon(): List<PokemonResult> = coroutineScope {

        val ids = (1..151).toList()

        val pokemons = ids.map { id ->
            async {

                val responseText = fetch("https://pokeapi.co/api/v2/pokemon/$id")

                val json = JSONObject(responseText)

                val idJson = json.getInt("id")
                val name = json.getString("name")
                val image = json.getJSONObject("sprites").getString("front_default")

                val typesArray = json.getJSONArray("types")
                val type1 =
                    typesArray.getJSONObject(0)
                        .getJSONObject("type")
                        .getString("name")

                val type2 =
                    if (typesArray.length() > 1)
                        typesArray.getJSONObject(1)
                            .getJSONObject("type")
                            .getString("name")
                    else
                        ""

                PokemonResult(
                    id = idJson,
                    name = name.replaceFirstChar { it.uppercase() },
                    image = image,
                    type = type1.replaceFirstChar { it.uppercase() },
                    type2 = type2.replaceFirstChar { it.uppercase() }
                )
            }
        }.awaitAll()

        pokemons.sortedBy { it.id }
    }
}
