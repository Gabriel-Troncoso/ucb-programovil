package org.ucb.appp1.catalog.screen.viewmodel

sealed interface CatalogEffect {
    data class ShowMessage(val message: String) : CatalogEffect
}
