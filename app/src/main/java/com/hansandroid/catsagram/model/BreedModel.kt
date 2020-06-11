package com.hansandroid.catsagram.model

import com.google.gson.annotations.SerializedName

data class BreedModel(
    val id: String,
    val name: String,
    val temperament: String,
    @SerializedName("life_span")
    val lifeSpan: String,
    @SerializedName("wikipedia_url")
    val wikiUrl: String,
    val origin: String,
    @SerializedName("energy_level")
    val energyLevel: Int,
    @SerializedName("social_needs")
    val socialNeeds: Int,
    @SerializedName("stranger_friendly")
    val strangerFriendly: Int,
    @SerializedName("child_friendly")
    val childFriendly: Int,
    val description: String
)