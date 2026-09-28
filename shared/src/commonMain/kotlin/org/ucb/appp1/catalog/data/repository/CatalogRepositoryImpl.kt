package org.ucb.appp1.catalog.data.repository

import org.ucb.appp1.catalog.data.datasource.CatalogRemoteDataSource
import org.ucb.appp1.catalog.data.mapper.toDomain
import org.ucb.appp1.catalog.domain.model.CatalogPageModel
import org.ucb.appp1.catalog.domain.repository.CatalogRepository

class CatalogRepositoryImpl(
    private val dataSource: CatalogRemoteDataSource
) : CatalogRepository {

    override suspend fun getPeople(page: Int): Result<CatalogPageModel> = runCatching {
        dataSource.fetchPeople(page).toDomain()
    }
}
