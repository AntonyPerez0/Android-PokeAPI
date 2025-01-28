// app/src/main/java/com/amp/pokeapi/TopBar.kt
package com.amp.pokeapi

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.*
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.style.TextOverflow
import androidx.navigation.NavController
import com.amp.pokeapi.viewmodels.TeamViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(navController: NavController, teamViewModel: TeamViewModel) {
    // Holds the current text input from the search field
    var searchQuery by remember { mutableStateOf("") }

    // This controls the scroll behavior for the top app bar
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())

    Scaffold(modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection), topBar = {
        Column {
            // MediumTopAppBar with custom colors
            MediumTopAppBar(colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color(0xFFCC0000),
                titleContentColor = Color.White,
                navigationIconContentColor = Color.White,
                actionIconContentColor = Color.White
            ), title = {
                Text(
                    text = "Red's PokeDex", maxLines = 1, overflow = TextOverflow.Ellipsis
                )
            }, navigationIcon = {
                // You can add a navigation icon here if needed
                // Currently left empty
            }, actions = {
                // Button to navigate to Team View
                IconButton(onClick = { navController.navigate("teamView") }) {
                    Icon(
                        imageVector = Icons.Filled.List, contentDescription = "View Team"
                    )
                }
            }, scrollBehavior = scrollBehavior
            )

            // Search field below the top bar
            TextField(value = searchQuery,
                onValueChange = { newQuery -> searchQuery = newQuery },
                placeholder = { Text("Search Pokémon") },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }) { innerPadding ->
        // Show the grid content, passing in both the padding from Scaffold and the search query
        ScrollContent(innerPadding, searchQuery, teamViewModel)
    }
}