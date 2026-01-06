package com.example.pokedex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.pokedex.api.PokemonResult
import com.example.pokedex.data.PokemonStore
import com.example.pokedex.ui.theme.PokedexTheme


class SavedPokemonsActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            PokedexTheme {

                val pokemons = remember { mutableStateListOf<PokemonResult>() }

                LaunchedEffect(Unit) {
                    pokemons.clear()
                    pokemons.addAll(PokemonStore.getAll())
                }

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {

                    Text("Pokémons Salvos")

                    Spacer(Modifier.height(12.dp))

                    pokemons.forEach { pokemon ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(4.dp)
                        ) {
                            Row(
                                modifier = Modifier.padding(8.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {

                                AsyncImage(
                                    model = pokemon.image,
                                    contentDescription = pokemon.name,
                                    modifier = Modifier.size(64.dp)
                                )

                                Spacer(Modifier.width(8.dp))

                                Text(pokemon.name, modifier = Modifier.weight(1f))

                                IconButton(onClick = {
                                    PokemonStore.remove(pokemon)
                                    pokemons.remove(pokemon)
                                }) {
                                    Text("❌")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
