package com.example.pokedex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.pokedex.api.PokemonApi
import com.example.pokedex.api.PokemonResult
import com.example.pokedex.ui.theme.PokedexTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            PokedexTheme {
                PokemonSearchScreen()
            }
        }
    }
}

@Composable
fun PokemonSearchScreen() {

    val scope = rememberCoroutineScope()
    val api = remember { PokemonApi() }

    var search by remember { mutableStateOf("") }
    var pokemon by remember { mutableStateOf<PokemonResult?>(null) }
    var loading by remember { mutableStateOf(false) }
    var error by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        OutlinedTextField(
            value = search,
            onValueChange = { search = it },
            label = { Text("Nome ou ID do Pokémon") },
            modifier = Modifier.fillMaxWidth().padding(0.dp, 48.dp, 0.dp)
        )

        Spacer(Modifier.height(12.dp))

        Button(
            onClick = {
                scope.launch {
                    try {
                        loading = true
                        pokemon = api.buscarPokemon(search.trim().lowercase())

                        error = null
                    } catch (e: Exception) {
                        error = "Este pokemon não existe"
                        pokemon = null
                    }
                    finally {
                        loading = false
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Buscar")
        }

        Spacer(Modifier.height(24.dp))

        when {
            loading -> CircularProgressIndicator()
            error != null -> Text(error!!, color = MaterialTheme.colorScheme.error)
            pokemon != null -> PokemonCard(pokemon!!)
        }
    }
}
@Composable
fun PokemonCard(pokemon: PokemonResult) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            AsyncImage(
                model = pokemon.image,
                contentDescription = pokemon.name,
                modifier = Modifier.size(120.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "#${pokemon.id} ${pokemon.name}",
                style = MaterialTheme.typography.titleMedium
            )

            Text(
                text = if (pokemon.type2.isNotEmpty())
                    "${pokemon.type} / ${pokemon.type2}"
                else
                    pokemon.type
            )
        }
    }
}

