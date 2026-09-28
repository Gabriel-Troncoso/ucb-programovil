package org.ucb.appp1.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.ucb.appp1.catalog.screen.CatalogScreen
import org.ucb.appp1.screens.ProfileEditScreen
import org.ucb.appp1.screens.ProfileScreen

@Composable
fun AppNavHost() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = NavRoute.Catalog
    ) {

        composable<NavRoute.Catalog> {
            CatalogScreen()
        }

        composable<NavRoute.Profile> {
            ProfileScreen(
                navController = navController
            )
        }

        composable<NavRoute.ProfileEdit> {
            ProfileEditScreen(
                navController = navController
            )
        }
    }
}
