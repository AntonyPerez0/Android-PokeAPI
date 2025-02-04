package com.amp.pokeapi

import com.amp.pokeapi.models.Pokemon
import com.amp.pokeapi.models.PokemonResponse
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

class PokeApiServiceTest {

    private lateinit var apiService: PokeApiService

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        apiService = mock(PokeApiService::class.java)
    }

    @Test
    fun testGetPokemonReturnsExpectedData(): Unit = runBlocking {
        // Arrange
        val expectedPokemonList = listOf(
            Pokemon(
                name = "bulbasaur",
                height = 7,
                weight = 69,
                baseExperience = 64,
                sprites = null,
                abilities = emptyList(),
                types = emptyList(),
                url = "https://pokeapi.co/api/v2/pokemon/1/"
            ),
            Pokemon(
                name = "ivysaur",
                height = 10,
                weight = 130,
                baseExperience = 142,
                sprites = null,
                abilities = emptyList(),
                types = emptyList(),
                url = "https://pokeapi.co/api/v2/pokemon/2/"
            )
        )
        val expectedResponse = PokemonResponse(results = expectedPokemonList)
        `when`(apiService.getPokemon(2)).thenReturn(expectedResponse)

        // Act
        val actualResponse = apiService.getPokemon(2)

        // Assert
        assertEquals(expectedResponse, actualResponse)
        verify(apiService).getPokemon(2)
    }
}