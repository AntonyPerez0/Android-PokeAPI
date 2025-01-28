// app/src/main/java/com/amp/pokeapi/MainActivity.kt
package com.amp.pokeapi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import com.amp.pokeapi.ui.theme.PokeAPITheme
import com.amp.pokeapi.viewmodels.TeamViewModel

class MainActivity : ComponentActivity() {

    // Initialize the TeamViewModel
    private val teamViewModel: TeamViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PokeAPITheme(dynamicColor = false) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Set up NavHost for navigation
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "pokemonList") {
                        composable("pokemonList") {
                            Column {
                                TopBar(navController, teamViewModel) // Pass navController to TopBar
                                // Rest of your content (Pokémon grid)
                            }
                        }
                        composable("teamView") {
                            TeamView(navController, teamViewModel)
                        }
                    }
                }
            }
        }
    }
}