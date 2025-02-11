package com.amp.pokeapi.extensions


fun String.capitalizeFirstLetter(): String {
    return if (this.isNotEmpty()) {
        this[0].uppercaseChar() + this.substring(1)
    } else {
        this
    }
}