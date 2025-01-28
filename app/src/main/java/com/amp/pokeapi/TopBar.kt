package com.amp.pokeapi

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
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

    var searchQuery by remember { mutableStateOf("") }


    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())

    Scaffold(modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection), topBar = {
        Column {

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

            }, actions = {

                IconButton(onClick = { navController.navigate("teamView") }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.List, contentDescription = "View Team"
                    )
                }
            }, scrollBehavior = scrollBehavior
            )


            TextField(value = searchQuery,
                onValueChange = { newQuery -> searchQuery = newQuery },
                placeholder = { Text("Search Pokémon") },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }) { innerPadding ->

        ScrollContent(innerPadding, searchQuery, teamViewModel)
    }
}