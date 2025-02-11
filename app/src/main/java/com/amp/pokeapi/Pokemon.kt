package com.amp.pokeapi.models

data class Pokemon(
    val name: String,
    val url: String,         // URL to fetch detailed data
    var height: Int = 0,
    var weight: Int = 0,
    var baseExperience: Int = 0,
    var abilities: List<Ability> = emptyList(),
    var types: List<Type> = emptyList(),
    var sprites: Sprites? = null
)

data class Ability(
    val is_hidden: Boolean, val slot: Int, val ability: AbilityDetail
)

data class AbilityDetail(
    val name: String, val url: String
)

data class Type(
    val slot: Int, val type: TypeDetail
)

data class TypeDetail(
    val name: String, val url: String
)

data class Sprites(
    val front_default: String?, val back_default: String?
)