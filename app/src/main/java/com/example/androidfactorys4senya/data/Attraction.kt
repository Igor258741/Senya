package com.example.androidfactorys4senya.data

import com.squareup.moshi.Json

data class Attraction(
    val description: String = "",
    val facts: List<String> = listOf(),
    val id: String = "",
    @Json(name = "image_urls")
    val image_urls: List<String> = listOf(),
    val location: Location = Location(),
    val months_to_visit: String = "",
    val title: String = ""
) {
    data class Location(
        val latitude: String = "",
        val longitude: String = ""
    )
}