package org.ucb.appp1.di

import org.koin.dsl.module
import org.ucb.appp1.catalog.data.datasource.CatalogRemoteDataSource
import org.ucb.appp1.catalog.data.repository.CatalogRepositoryImpl
import org.ucb.appp1.catalog.data.service.CatalogService
import org.ucb.appp1.catalog.domain.repository.CatalogRepository

val dataModule = module {
    single { createHttpClient() }
    single<CatalogRemoteDataSource> { CatalogService(get()) }
    single<CatalogRepository> { CatalogRepositoryImpl(get()) }
}
