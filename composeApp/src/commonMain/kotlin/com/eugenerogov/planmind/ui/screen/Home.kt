package com.eugenerogov.planmind.ui.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.eugenerogov.planmind.Greeting
import com.eugenerogov.planmind.ui.theme.LocalColorsPalette
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

import planmind.composeapp.generated.resources.Res
import planmind.composeapp.generated.resources.compose_multiplatform

object HomeScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        HomeContent(
            onNavigateToLogin = { navigator.push(LoginScreen) }
        )
    }
}

@Composable
@Preview
fun HomeContent(
    onNavigateToLogin: () -> Unit = {}
) {
    MaterialTheme {
        var showContent by remember { mutableStateOf(false) }

        Scaffold(
            containerColor = LocalColorsPalette.current.background
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .safeContentPadding()
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {


                Button(onClick = onNavigateToLogin) {
                    Text("Go to Login")
                }

                AnimatedVisibility(showContent) {
                    val greeting = remember { Greeting().greet() }
                    Column(
                        Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(painterResource(Res.drawable.compose_multiplatform), null)
                        Text("Compose: $greeting")
                    }
                }
            }
        }
    }
}