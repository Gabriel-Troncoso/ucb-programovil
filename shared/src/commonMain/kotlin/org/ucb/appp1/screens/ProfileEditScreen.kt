package org.ucb.appp1.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

@Composable
fun ProfileEditScreen(
    navController: NavHostController
) {
    Column {

        Text("Pantalla Editar Perfil")

        Button(
            onClick = {
                navController.popBackStack()
            }
        ) {
            Text("Volver")
        }
    }
}