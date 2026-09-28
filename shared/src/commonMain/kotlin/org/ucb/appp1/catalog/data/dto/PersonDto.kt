package org.ucb.appp1.catalog.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PersonDto(
    val name: String = "",
    val height: String = "unknown",
    val mass: String = "unknown",
    @SerialName("hair_color")
    val hairColor: String = "unknown",
    @SerialName("skin_color")
    val skinColor: String = "unknown",
    @SerialName("eye_color")
    val eyeColor: String = "unknown",
    val gender: String = "unknown",
    val url: String = ""
)
