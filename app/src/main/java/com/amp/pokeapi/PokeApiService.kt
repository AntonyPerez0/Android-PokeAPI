// app/src/main/java/com/amp/pokeapi/PokeApiService.kt
package com.amp.pokeapi

import com.amp.pokeapi.models.Pokemon
import com.amp.pokeapi.models.PokemonResponse
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface PokeApiService {
    @GET("pokemon")
    suspend fun getPokemon(@Query("limit") limit: Int): PokemonResponse

    @GET
    suspend fun getPokemonDetails(@Url url: String): Pokemon
}