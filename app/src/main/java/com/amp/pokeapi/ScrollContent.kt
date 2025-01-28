// app/src/main/java/com/amp/pokeapi/ScrollContent.kt
package com.amp.pokeapi

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.amp.pokeapi.models.Pokemon
import com.amp.pokeapi.viewmodels.TeamViewModel
import kotlinx.coroutines.launch

@Composable
fun ScrollContent(
    innerPadding: PaddingValues,
    searchQuery: String,
    teamViewModel: TeamViewModel
) {
    // Holds the final list of detailed Pokemon objects
    var pokemonList by remember { mutableStateOf<List<Pokemon>>(emptyList()) }
    val coroutineScope = rememberCoroutineScope()

    // Snackbar to show feedback messages
    val snackbarHostState = remember { SnackbarHostState() }

    // This effect triggers a network call to fetch the first 151 Pokémon + details
    LaunchedEffect(Unit) {
        coroutineScope.launch {
            try {
                // 1) Get the initial list (returns name + url)
                val response = RetrofitInstance.api.getPokemon(limit = 151)

                // 2) For each, get the detailed object
                val detailedList = response.results.map { basicPkm ->
                    RetrofitInstance.api.getPokemonDetails(basicPkm.url)
                }

                pokemonList = detailedList
            } catch (e: Exception) {
                // Handle error state (you could show a SnackBar, etc.)
                e.printStackTrace()
                coroutineScope.launch {
                    snackbarHostState.showSnackbar("Failed to load Pokémon data.")
                }
            }
        }
    }

    // Filter the list by user-entered search (case-insensitive)
    val filteredList = remember(searchQuery, pokemonList) {
        pokemonList.filter {
            it.name.contains(searchQuery, ignoreCase = true)
        }
    }

    // Display the filtered results as a two-column lazy grid
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = innerPadding,
        modifier = Modifier.padding(8.dp)
    ) {
        items(filteredList) { pkm ->
            val isAdded = teamViewModel.team.any { it.name == pkm.name }
            PokemonItem(
                pokemon = pkm,
                onAddToTeam = { pokemon ->
                    val success = teamViewModel.addToTeam(pokemon)
                    coroutineScope.launch {
                        if (success) {
                            snackbarHostState.showSnackbar("${pokemon.name.capitalizeFirstLetter()} added to your team!")
                        } else {
                            // Determine the reason for failure
                            val message = when {
                                teamViewModel.team.size >= 5 -> "Your team is full (5 Pokémon)."
                                teamViewModel.team.any { it.name == pokemon.name } -> "${pokemon.name.capitalizeFirstLetter()} is already in your team."
                                else -> "Cannot add ${pokemon.name.capitalizeFirstLetter()} to your team."
                            }
                            snackbarHostState.showSnackbar(message)
                        }
                    }
                },
                isAdded = isAdded
            )
        }
    }

    // Display Snackbar
    SnackbarHost(hostState = snackbarHostState)
}