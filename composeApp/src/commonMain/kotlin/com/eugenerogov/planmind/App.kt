package com.eugenerogov.planmind

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import com.eugenerogov.planmind.ui.screen.HomeScreen

@Composable
fun PlanMindApp() {
    initKoin()
    Navigator(HomeScreen)
}