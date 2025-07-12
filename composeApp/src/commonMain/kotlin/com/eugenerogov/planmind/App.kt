package com.eugenerogov.planmind

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import com.eugenerogov.planmind.ui.screen.HomeScreen
import com.eugenerogov.planmind.ui.theme.PlanMindTheme

@Composable
fun PlanMindApp() {
    initKoin()
    PlanMindTheme {
        Navigator(HomeScreen)
    }
}