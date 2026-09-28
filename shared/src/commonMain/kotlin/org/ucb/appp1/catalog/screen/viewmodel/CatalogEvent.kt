package org.ucb.appp1.catalog.screen.viewmodel

sealed interface CatalogEvent {
    data object LoadCatalog : CatalogEvent
    data object LoadNextPage : CatalogEvent
    data object Retry : CatalogEvent
}
