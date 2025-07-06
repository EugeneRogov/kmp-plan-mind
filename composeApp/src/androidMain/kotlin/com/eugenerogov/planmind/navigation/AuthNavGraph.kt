package com.eugenerogov.planmind.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.eugenerogov.planmind.ui.screen.DefaultScreen
import com.eugenerogov.planmind.ui.screen.LoginScreen

fun NavGraphBuilder.authNavGraph(navController: NavController, goToMain: () -> Unit) {
    composable(
        route = Screen.Default.route
    ) {
        DefaultScreen()
    }

    composable(
        route = Screen.Login.route
    ) {
        LoginScreen(
//            navController = navController,
            goToMain = {
                goToMain.invoke()
            }
        )
    }

    composable(
        route = Screen.RecoverPassword.route
    ) {
//        RecoverPasswordScreen(
//            navController = navController,
//            goToMain = {}
//        )
    }

    composable(
        route = Screen.RecoverPasswordSuccess.route
    ) {
//        RecoverPasswordSuccessScreen()
    }
}
