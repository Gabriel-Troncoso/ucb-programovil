package org.ucb.appp1.catalog.domain.repository

import org.ucb.appp1.catalog.domain.model.CatalogPageModel

interface CatalogRepository {
    suspend fun getPeople(page: Int = 1): Result<CatalogPageModel>
}
