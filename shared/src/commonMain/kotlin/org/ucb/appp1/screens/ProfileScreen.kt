package org.ucb.appp1.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import org.ucb.appp1.navigation.NavRoute

@Composable
fun ProfileScreen(
    navController: NavHostController
) {
    Column {

        Text("Pantalla Profile")

        Button(
            onClick = {
                navController.navigate(NavRoute.ProfileEdit)
            }
        ) {
            Text("Editar perfil")
        }
    }
}