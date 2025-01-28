// app/src/main/java/com/amp/pokeapi/TeamView.kt
package com.amp.pokeapi

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import androidx.navigation.NavController
import com.amp.pokeapi.extensions.capitalizeFirstLetter
import com.amp.pokeapi.models.Pokemon
import com.amp.pokeapi.viewmodels.TeamViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TeamView(navController: NavController, teamViewModel: TeamViewModel) {
    val team = teamViewModel.team

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Your Team") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        },
        content = { innerPadding ->
            if (team.isEmpty()) {
                // Show a message if the team is empty
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "Your team is empty. Add some Pokémon!", fontSize = 18.sp)
                }
            } else {
                // Display the team
                LazyColumn(
                    contentPadding = innerPadding,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    items(team) { pkm ->
                        TeamMemberItem(pkm, teamViewModel)
                    }
                }
            }
        }
    )
}

@Composable
fun TeamMemberItem(pokemon: Pokemon, teamViewModel: TeamViewModel) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(16.dp)
        ) {
            // Pokémon Image
            val spriteUrl = pokemon.sprites?.front_default
            if (spriteUrl != null) {
                Image(
                    painter = rememberAsyncImagePainter(spriteUrl),
                    contentDescription = "Pokemon Image",
                    modifier = Modifier.size(80.dp)
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            // Pokémon Details
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = pokemon.name.capitalizeFirstLetter(),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(text = "Height: ${pokemon.height}")
                Text(text = "Weight: ${pokemon.weight}")
            }

            // Remove Button
            IconButton(onClick = { teamViewModel.removeFromTeam(pokemon) }) {
                Icon(
                    imageVector = Icons.Filled.Close,
                    contentDescription = "Remove from Team"
                )
            }
        }
    }
}

// Removed the duplicate capitalizeFirstLetter() function