package com.amp.pokeapi

import androidx.activity.compose.setContent
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.amp.pokeapi.models.Pokemon
import com.amp.pokeapi.viewmodels.TeamViewModel
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PokemonItemInstrumentedTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun testAddPokemonToTeam() {
        // Arrange
        val teamViewModel = TeamViewModel()
        val pokemon = Pokemon(
            name = "bulbasaur",
            url = "https://pokeapi.co/api/v2/pokemon/1/",
            height = 7,
            weight = 69,
            baseExperience = 64
        )

        // Act
        composeTestRule.activity.setContent {
            PokemonItem(
                pokemon = pokemon,
                onAddToTeam = { teamViewModel.addToTeam(it) },
                isAdded = false
            )
        }

        // Assert
        composeTestRule.onNodeWithText("Add to Team").performClick()
        assert(teamViewModel.team.contains(pokemon))
    }
}