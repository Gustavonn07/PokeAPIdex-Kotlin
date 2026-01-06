package com.example.pokedex

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
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

    val context = LocalContext.current
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
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 48.dp)
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
                        error = "Este Pokémon não existe"
                        pokemon = null
                    } finally {
                        loading = false
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Buscar")
        }

        Spacer(Modifier.height(16.dp))

        Button(
            onClick = {
                context.startActivity(
                    Intent(context, SavedPokemonsActivity::class.java)
                )
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Ver Pokémons Salvos")
        }

        Spacer(Modifier.height(24.dp))

        when {
            loading -> CircularProgressIndicator()
            error != null -> Text(error!!, color = MaterialTheme.colorScheme.error)
            pokemon != null -> PokemonCard(
                pokemon = pokemon!!,
                onAddClick = {
                    val intent = Intent(context, DetailActivity::class.java)
                    intent.putExtra("pokemon", pokemon)
                    context.startActivity(intent)
                }
            )
        }
    }
}

@Composable
fun PokemonCard(
    pokemon: PokemonResult,
    onAddClick: () -> Unit
) {
    val context = LocalContext.current

    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            AsyncImage(
                model = pokemon.image,
                contentDescription = pokemon.name,
                modifier = Modifier.size(120.dp)
            )

            Text("#${pokemon.id} ${pokemon.name}")

            Text(
                if (pokemon.type2.isNotEmpty())
                    "${pokemon.type} / ${pokemon.type2}"
                else pokemon.type
            )

            Spacer(Modifier.height(8.dp))

            Button(onClick = {
                val intent = Intent(context, DetailActivity::class.java)
                intent.putExtra("pokemon", pokemon)
                context.startActivity(intent)
            }) {
                Text("+ Adicionar")
            }
        }
    }
}