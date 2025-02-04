package com.amp.pokeapi

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.amp.pokeapi.extensions.capitalizeFirstLetter
import com.amp.pokeapi.models.Pokemon
import com.google.accompanist.flowlayout.FlowRow

@Composable
fun PokemonItem(
    pokemon: Pokemon,
    onAddToTeam: (Pokemon) -> Unit,
    isAdded: Boolean,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .padding(8.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Show the main (front) sprite
            val spriteUrl = pokemon.sprites?.front_default
            if (spriteUrl != null) {
                Image(
                    painter = rememberAsyncImagePainter(spriteUrl),
                    contentDescription = "Pokemon Image",
                    modifier = Modifier.size(100.dp)
                )
            }

            // Name
            Text(
                text = pokemon.name.capitalizeFirstLetter(),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 8.dp, bottom = 4.dp)
            )

            // Height, weight, and base experience
            Text(
                text = "Height: ${pokemon.height}",
                fontSize = 14.sp,
                modifier = Modifier.padding(bottom = 2.dp)
            )
            Text(
                text = "Weight: ${pokemon.weight}",
                fontSize = 14.sp,
                modifier = Modifier.padding(bottom = 2.dp)
            )
            Text(
                text = "Base Experience: ${pokemon.baseExperience}",
                fontSize = 14.sp,
                modifier = Modifier.padding(bottom = 4.dp)
            )

            // Abilities
            Text(
                text = "Abilities:",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 2.dp)
            )
            val abilityList = pokemon.abilities.joinToString(", ") { it.ability.name }
            Text(
                text = abilityList,
                fontSize = 14.sp,
                modifier = Modifier.padding(bottom = 4.dp)
            )

            // Types
            val typeList = pokemon.types.joinToString { it.type.name }
            Text(
                text = "Types: $typeList",
                fontSize = 14.sp,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            // Add to Team Button
            Button(
                onClick = { onAddToTeam(pokemon) }, enabled = !isAdded
            ) {
                Text(if (isAdded) "Added" else "Add to Team")
            }
        }
    }
}
