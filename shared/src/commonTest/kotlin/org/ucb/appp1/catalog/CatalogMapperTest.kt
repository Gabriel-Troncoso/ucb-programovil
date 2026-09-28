package org.ucb.appp1.catalog

import org.ucb.appp1.catalog.data.dto.CatalogDto
import org.ucb.appp1.catalog.data.dto.PersonDto
import org.ucb.appp1.catalog.data.mapper.toDomain
import kotlin.test.Test
import kotlin.test.assertEquals

class CatalogMapperTest {
    @Test
    fun mapsSwapiFieldsAndPagination() {
        val result = CatalogDto(
            count = 82,
            next = "https://swapi.dev/api/people/?page=2",
            results = listOf(
                PersonDto(
                    name = "Luke Skywalker",
                    height = "172",
                    mass = "77",
                    hairColor = "blond",
                    skinColor = "fair",
                    eyeColor = "blue",
                    gender = "male",
                    url = "https://swapi.dev/api/people/1/"
                )
            )
        ).toDomain()

        assertEquals(82, result.totalCount)
        assertEquals(2, result.nextPage)
        assertEquals(1, result.people.single().id)
        assertEquals("Luke Skywalker", result.people.single().name)
        assertEquals("blue", result.people.single().eyeColor)
    }
}
