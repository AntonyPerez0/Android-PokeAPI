// app/src/main/java/com/amp/pokeapi/viewmodels/TeamViewModel.kt
package com.amp.pokeapi.viewmodels

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.amp.pokeapi.models.Pokemon

class TeamViewModel : ViewModel() {

    // Holds the list of Pokémon in the team
    private val _team = mutableStateListOf<Pokemon>()
    val team: List<Pokemon> get() = _team

    // Maximum team size
    private val maxTeamSize = 5

    // Adds a Pokémon to the team if constraints are met
    fun addToTeam(pokemon: Pokemon): Boolean {
        if (_team.size >= maxTeamSize) {
            return false // Team is full
        }
        if (_team.any { it.name == pokemon.name }) {
            return false // Duplicate Pokémon
        }
        _team.add(pokemon)
        return true
    }

    // Removes a Pokémon from the team
    fun removeFromTeam(pokemon: Pokemon) {
        _team.remove(pokemon)
    }
}