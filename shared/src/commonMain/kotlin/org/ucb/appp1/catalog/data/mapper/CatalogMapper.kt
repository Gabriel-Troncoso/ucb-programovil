package org.ucb.appp1.catalog.data.mapper

import org.ucb.appp1.catalog.data.dto.CatalogDto
import org.ucb.appp1.catalog.data.dto.PersonDto
import org.ucb.appp1.catalog.domain.model.CatalogPageModel
import org.ucb.appp1.catalog.domain.model.PersonModel

fun CatalogDto.toDomain(): CatalogPageModel = CatalogPageModel(
    people = results.map(PersonDto::toDomain),
    nextPage = next.extractPageNumber(),
    totalCount = count
)

private fun PersonDto.toDomain(): PersonModel = PersonModel(
    id = url.trimEnd('/').substringAfterLast('/').toIntOrNull() ?: name.hashCode(),
    name = name,
    height = height,
    mass = mass,
    hairColor = hairColor,
    skinColor = skinColor,
    eyeColor = eyeColor,
    gender = gender
)

private fun String?.extractPageNumber(): Int? =
    this?.substringAfter("page=", missingDelimiterValue = "")?.toIntOrNull()
