package com.amp.pokeapi.viewmodels

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.amp.pokeapi.models.Pokemon

class TeamViewModel : ViewModel() {


    private val _team = mutableStateListOf<Pokemon>()
    val team: List<Pokemon> get() = _team

    var isLoading = mutableStateOf(false)
        private set


    private val maxTeamSize = 5


    fun addToTeam(pokemon: Pokemon): Boolean {
        if (_team.size >= maxTeamSize) {
            return false // Team is full
        }
        if (_team.any { it.name == pokemon.name }) {
            return false
        }
        _team.add(pokemon)
        return true
    }


    fun removeFromTeam(pokemon: Pokemon) {
        _team.remove(pokemon)
    }


    fun setLoading(value: Boolean) {
        isLoading.value = value
    }
}