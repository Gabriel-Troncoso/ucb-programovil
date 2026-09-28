package org.ucb.appp1.catalog.screen.viewmodel

import org.ucb.appp1.catalog.domain.model.PersonModel

data class CatalogState(
    val people: List<PersonModel> = emptyList(),
    val isLoading: Boolean = true,
    val isLoadingMore: Boolean = false,
    val errorMessage: String? = null,
    val nextPage: Int? = 1,
    val totalCount: Int = 0
)
