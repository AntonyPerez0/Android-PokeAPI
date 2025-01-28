// app/src/main/java/com/amp/pokeapi/extensions/StringExtensions.kt
package com.amp.pokeapi.extensions

/**
 * Extension function to capitalize the first letter of a string.
 * Returns the original string if it's empty.
 */
fun String.capitalizeFirstLetter(): String {
    return if (this.isNotEmpty()) {
        this[0].uppercaseChar() + this.substring(1)
    } else {
        this
    }
}