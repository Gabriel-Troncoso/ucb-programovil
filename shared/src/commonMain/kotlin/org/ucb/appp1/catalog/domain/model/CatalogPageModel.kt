package org.ucb.appp1.catalog.domain.model

data class CatalogPageModel(
    val people: List<PersonModel>,
    val nextPage: Int?,
    val totalCount: Int
)
