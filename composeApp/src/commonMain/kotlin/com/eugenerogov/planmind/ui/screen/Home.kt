package com.eugenerogov.planmind.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.eugenerogov.planmind.ui.theme.LocalColorsPalette
import org.jetbrains.compose.ui.tooling.preview.Preview

object HomeScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        HomeContent(
            onNavigateToLogin = { navigator.push(LoginScreen) },
            onNavigateToProfile = { navigator.push(ProfileScreen) },
            initialTab = 0
        )
    }
}

object HomeWithProfileScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        HomeContent(
            onNavigateToLogin = { navigator.push(LoginScreen) },
            onNavigateToProfile = { navigator.push(ProfileScreen) },
            initialTab = 1
        )
    }
}

@Composable
fun HomeContent(
    onNavigateToLogin: () -> Unit = {},
    onNavigateToProfile: () -> Unit = {},
    initialTab: Int = 0
) {
    val navigator = LocalNavigator.currentOrThrow
    var selectedTab by remember { mutableStateOf(initialTab) }

    Scaffold(
        containerColor = LocalColorsPalette.current.background,
        bottomBar = {
            NavigationBar(
                containerColor = LocalColorsPalette.current.surface
            ) {
                NavigationBarItem(
                    icon = {
                    Text("🏠")
                    },
                    label = { Text("Главная") },
                    selected = selectedTab == 0,
                    onClick = { selectedTab = 0 }
                )
                NavigationBarItem(
                    icon = {
                    Text("👤")
                    },
                    label = { Text("Профиль") },
                    selected = selectedTab == 1,
                    onClick = { selectedTab = 1 }
                )
            }
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            when (selectedTab) {
                0 -> MainScreenTab(
                    onNavigateToLogin = {
                        onNavigateToLogin()
                    },
                    onNavigateToProfile = onNavigateToProfile
                )
                1 -> ProfileScreenContent(
                    onNavigateBack = { selectedTab = 0 },
                    onNavigateToAuth = {
                        navigator.replaceAll(HomeWithProfileScreen)
                        navigator.push(LoginScreen)
                    }
                )
                else -> MainScreenTab(
                    onNavigateToLogin = onNavigateToLogin,
                    onNavigateToProfile = onNavigateToProfile
                )
            }
        }
    }
}

@Composable
private fun MainScreenTab(
    onNavigateToLogin: () -> Unit = {},
    onNavigateToProfile: () -> Unit = {}
) {
    MainScreen()
}

@Preview
@Composable
fun HomePreview() {
    MaterialTheme {
        HomeContent()
    }
}