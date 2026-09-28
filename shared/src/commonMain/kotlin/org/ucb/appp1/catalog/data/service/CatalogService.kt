package org.ucb.appp1.catalog.data.service

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import org.ucb.appp1.catalog.data.datasource.CatalogRemoteDataSource
import org.ucb.appp1.catalog.data.dto.CatalogDto

class CatalogService(
    private val client: HttpClient
) : CatalogRemoteDataSource {

    override suspend fun fetchPeople(page: Int): CatalogDto =
        client.get("https://swapi.dev/api/people/") {
            parameter("page", page)
        }.body()
}
