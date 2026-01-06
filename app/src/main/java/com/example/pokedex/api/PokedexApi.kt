package com.example.pokedex.api

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL

class PokemonApi {

    private fun fetch(urlStr: String): String {
        val url = URL(urlStr)
        val connection = url.openConnection() as HttpURLConnection

        return connection.run {
            requestMethod = "GET"
            connectTimeout = 5000
            readTimeout = 5000
            inputStream.bufferedReader().use { it.readText() }
        }
    }

    suspend fun buscarPokemon(search: String): PokemonResult =
        withContext(Dispatchers.IO) {

            val responseText =
                fetch("https://pokeapi.co/api/v2/pokemon/${search.trim().lowercase()}")

            val json = JSONObject(responseText)

            val id = json.getInt("id")
            val name = json.getString("name")
            val image =
                json.getJSONObject("sprites")
                    .getString("front_default")

            val typesArray = json.getJSONArray("types")

            val type1 = typesArray
                .getJSONObject(0)
                .getJSONObject("type")
                .getString("name")

            val type2 =
                if (typesArray.length() > 1)
                    typesArray
                        .getJSONObject(1)
                        .getJSONObject("type")
                        .getString("name")
                else
                    ""

            PokemonResult(
                id = id,
                name = name.replaceFirstChar { it.uppercase() },
                image = image,
                type = type1.replaceFirstChar { it.uppercase() },
                type2 = type2.replaceFirstChar { it.uppercase() }
            )
        }
}
