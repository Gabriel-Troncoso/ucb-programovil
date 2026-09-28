package org.ucb.appp1.catalog.data.datasource

import org.ucb.appp1.catalog.data.dto.CatalogDto

interface CatalogRemoteDataSource {
    suspend fun fetchPeople(page: Int = 1): CatalogDto
}
