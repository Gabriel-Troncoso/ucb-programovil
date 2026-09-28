package org.ucb.appp1.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class NavRoute {

    @Serializable
    data object Catalog : NavRoute()

    @Serializable
    data object Profile : NavRoute()

    @Serializable
    data object ProfileEdit : NavRoute()
}
