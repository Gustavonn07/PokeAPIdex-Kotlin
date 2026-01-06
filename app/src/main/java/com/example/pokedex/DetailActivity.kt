package com.example.pokedex

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.pokedex.api.PokemonResult
import com.example.pokedex.data.PokemonStore
import com.example.pokedex.ui.theme.PokedexTheme

class DetailActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val pokemon = intent.getSerializableExtra("pokemon") as PokemonResult

        setContent {
            PokedexTheme {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    AsyncImage(
                        model = pokemon.image,
                        contentDescription = pokemon.name,
                        modifier = Modifier.size(160.dp)
                    )

                    Text("#${pokemon.id} ${pokemon.name}")
                    Text("${pokemon.type} ${pokemon.type2}")

                    Spacer(Modifier.height(16.dp))

                    Button(onClick = {
                        val success = PokemonStore.add(pokemon)

                        Toast.makeText(
                            this@DetailActivity,
                            if (success) "Pokémon salvo!" else "Limite de 6 atingido",
                            Toast.LENGTH_SHORT
                        ).show()

                        finish()
                    }) {
                        Text("Salvar Pokémon")
                    }

                }
            }
        }
    }
}
