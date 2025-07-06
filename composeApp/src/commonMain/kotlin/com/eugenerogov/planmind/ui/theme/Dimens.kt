package com.eugenerogov.planmind.ui.theme

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.eugenerogov.planmind.Dimensions

val LocalDim = compositionLocalOf { Dimensions() }

data class Dimensions(
    val default: Dp = 0.dp,
    val borderStroke: Dp = 1.dp,
    val buttonHeight: Dp = 30.dp,
    val roundingHuge: Dp = 56.dp,
    val roundingLargePost: Dp = 32.dp,
    val roundingSmallPostMenu: Dp = 24.dp,
    val roundingLargeButtonInput: Dp = 20.dp,
    val roundingPhotoMediumButton: Dp = 16.dp,
    val roundingAvatarSmallButton: Dp = 12.dp,
    val roundingInput: Dp = 12.dp,
    val roundingInputSearch: Dp = 24.dp,
    val avatar: Dp = 48.dp,
    val iconSmall: Dp = 16.dp,
    val logo: Dp = 150.dp,
    val configFile: Dp = 150.dp,
    val taskTrackerIcon: Dp = 96.dp,
    val menuIcon: Dp = 24.dp,
    val drawerWidth: Dp = 300.dp,
    val largeX: Dp = 64.dp,
    val mediumX: Dp = 40.dp,
    val medium: Dp = 32.dp,
    val small: Dp = 24.dp,
    val smallX: Dp = 16.dp,
    val small2X: Dp = 12.dp,
    val small3X: Dp = 8.dp,
    val small4X: Dp = 4.dp,
    val small5X: Dp = 2.dp,
    val modalDialogAlpha: Float = 0.3f,
    val dialogBackgroundBlur: Dp = 16.dp,
    val cardElevation: Dp = 1.dp
)
