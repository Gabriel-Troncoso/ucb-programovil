package org.ucb.appp1.catalog.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class CatalogDto(
    val count: Int = 0,
    val next: String? = null,
    val previous: String? = null,
    val results: List<PersonDto> = emptyList()
)
