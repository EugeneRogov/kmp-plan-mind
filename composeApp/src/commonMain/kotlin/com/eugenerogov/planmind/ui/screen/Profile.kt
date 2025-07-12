package com.eugenerogov.planmind.ui.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.OutlinedButton
import com.eugenerogov.planmind.ui.component.input.InputField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import com.eugenerogov.planmind.ui.component.input.InputEmail
import com.eugenerogov.planmind.ui.theme.LocalColorsPalette
import com.eugenerogov.planmind.ui.theme.LocalDim
import com.eugenerogov.planmind.viewmodel.ProfileUiState
import com.eugenerogov.planmind.viewmodel.ProfileViewModel
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.core.parameter.parametersOf
import org.koin.mp.KoinPlatformTools
import planmind.composeapp.generated.resources.Res
import planmind.composeapp.generated.resources.back
import planmind.composeapp.generated.resources.profile
import planmind.composeapp.generated.resources.save
import planmind.composeapp.generated.resources.edit
import planmind.composeapp.generated.resources.first_name_label
import planmind.composeapp.generated.resources.first_name_placeholder
import planmind.composeapp.generated.resources.last_name_label
import planmind.composeapp.generated.resources.last_name_placeholder
import planmind.composeapp.generated.resources.email_hint
import planmind.composeapp.generated.resources.saving
import planmind.composeapp.generated.resources.sign_in_to_account
import planmind.composeapp.generated.resources.login_to_access_profile
import planmind.composeapp.generated.resources.login_to_account
import planmind.composeapp.generated.resources.or
import planmind.composeapp.generated.resources.sign_in_with_google
import planmind.composeapp.generated.resources.sign_in_with_vk

object ProfileScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        ProfileScreenContent(
            onNavigateBack = { navigator.pop() },
            onNavigateToAuth = { navigator.push(LoginScreen) }
        )
    }
}

@Composable
fun ProfileScreenContent(
    onNavigateBack: () -> Unit = {},
    onNavigateToAuth: () -> Unit = {}
) {
    val snackBarHostState = remember { SnackbarHostState() }

    val profileViewModel = remember {
        KoinPlatformTools.defaultContext().get().get<ProfileViewModel>(
            parameters = {
                parametersOf(
                    DefaultComponentContext(lifecycle = LifecycleRegistry()),
                    { /* Profile saved callback */ },
                    onNavigateToAuth
                )
            }
        )
    }

    var state by remember { mutableStateOf(ProfileUiState()) }

    LaunchedEffect(profileViewModel) {
        profileViewModel.checkAuthStatus()
        profileViewModel.state.subscribe { newState ->
            state = newState
        }
    }

    LaunchedEffect(key1 = state.errorMessage) {
        if (state.errorMessage.isNotEmpty()) {
            snackBarHostState.showSnackbar(state.errorMessage)
        }
    }

    if (state.isAuthenticated) {
        AuthenticatedProfileContent(
            state = state,
            viewModel = profileViewModel,
            onNavigateBack = onNavigateBack
        )
    } else {
        UnauthenticatedProfileContent(
            onGoToAuth = onNavigateToAuth,
            onGoogleSignIn = profileViewModel::signInWithGoogle,
            onVKSignIn = profileViewModel::signInWithVK
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AuthenticatedProfileContent(
    state: ProfileUiState,
    viewModel: ProfileViewModel,
    onNavigateBack: () -> Unit
) {
    val dim = LocalDim.current

    Scaffold(
        containerColor = LocalColorsPalette.current.background,
        snackbarHost = { SnackbarHost(hostState = remember { SnackbarHostState() }) },
        topBar = {
            TopAppBar(
                title = { Text(stringResource(Res.string.profile)) },
                navigationIcon = {
                    TextButton(onClick = onNavigateBack) {
                        Text(stringResource(Res.string.back))
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    if (state.isEditing) {
                        viewModel.saveProfile()
                    } else {
                        viewModel.toggleEditing()
                    }
                },
                containerColor = LocalColorsPalette.current.primary
            ) {
                Text(
                    text = if (state.isEditing) stringResource(Res.string.save) else stringResource(
                        Res.string.edit
                    ),
                    color = LocalColorsPalette.current.onPrimary
                )
            }
        }
    ) { innerPadding ->
        if (state.isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
                    .padding(dim.smallX),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(dim.smallX)
            ) {
                Spacer(modifier = Modifier.height(dim.medium))

                // Avatar
                Box(
                    modifier = Modifier
                        .size(dim.avatarLarge)
                        .clip(CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    if (state.avatar != null) {
                        // Here you would load the actual image
                        // For now, we'll use a placeholder
                        Text(
                            text = "👤",
                            fontSize = 80.sp,
                            color = LocalColorsPalette.current.onSurface
                        )
                    } else {
                        Text(
                            text = "👤",
                            fontSize = 80.sp,
                            color = LocalColorsPalette.current.onSurface
                        )
                    }
                }

                // Name display or edit
                if (state.isEditing) {
                    // First Name
                    InputField(
                        value = state.firstName,
                        onValueChange = viewModel::updateFirstName,
                        label = stringResource(Res.string.first_name_label),
                        placeholder = stringResource(Res.string.first_name_placeholder),
                        modifier = Modifier.fillMaxWidth(),
                        enabled = !state.isSaving
                    )

                    Spacer(modifier = Modifier.height(dim.smallX))

                    // Last Name
                    InputField(
                        value = state.lastName,
                        onValueChange = viewModel::updateLastName,
                        label = stringResource(Res.string.last_name_label),
                        placeholder = stringResource(Res.string.last_name_placeholder),
                        modifier = Modifier.fillMaxWidth(),
                        enabled = !state.isSaving
                    )
                } else {
                    // Display name
                    Text(
                        text = "${state.firstName} ${state.lastName}",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = LocalColorsPalette.current.onSurface
                    )
                }

                // Email
                if (state.isEditing) {
                    InputEmail(
                        hint = stringResource(Res.string.email_hint),
                        modifier = Modifier.fillMaxWidth(),
                        text = state.email,
                        onValueChange = viewModel::updateEmail,
                        inputType = KeyboardType.Email,
                        enabled = !state.isSaving
                    )
                } else {
                    Text(
                        text = state.email,
                        fontSize = 16.sp,
                        color = LocalColorsPalette.current.onSurface.copy(alpha = 0.7f)
                    )
                }

                if (state.isSaving) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(dim.small3X)
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(dim.smallX)
                        )
                        Text(
                            text = stringResource(Res.string.saving),
                            color = LocalColorsPalette.current.onSurface
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun UnauthenticatedProfileContent(
    onGoToAuth: () -> Unit,
    onGoogleSignIn: () -> Unit = {},
    onVKSignIn: () -> Unit = {}
) {
    val dim = LocalDim.current

    Scaffold(
        containerColor = LocalColorsPalette.current.background
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(dim.smallX),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "👤",
                fontSize = 80.sp,
                color = LocalColorsPalette.current.onSurface
            )

            Spacer(modifier = Modifier.height(dim.medium))

            Text(
                text = stringResource(Res.string.sign_in_to_account),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = LocalColorsPalette.current.onSurface
            )

            Spacer(modifier = Modifier.height(dim.small))

            Text(
                text = stringResource(Res.string.login_to_access_profile),
                fontSize = 16.sp,
                color = LocalColorsPalette.current.onSurface.copy(alpha = 0.7f),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(dim.medium))

            // Основная кнопка авторизации в современном стиле
            Button(
                onClick = onGoToAuth,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(dim.buttonHeightLarge),
                shape = RoundedCornerShape(dim.roundingInput),
                colors = ButtonDefaults.buttonColors(
                    containerColor = LocalColorsPalette.current.primary,
                    contentColor = LocalColorsPalette.current.onPrimary
                ),
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = dim.elevationSmall,
                    pressedElevation = dim.elevationExtra
                )
            ) {
                Text(
                    text = stringResource(Res.string.login_to_account),
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp
                )
            }

            Spacer(modifier = Modifier.height(dim.medium))

            // Разделитель "или" 
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(dim.borderStroke)
                        .background(LocalColorsPalette.current.onSurface.copy(alpha = 0.2f))
                )
                Text(
                    text = stringResource(Res.string.or),
                    modifier = Modifier.padding(horizontal = dim.smallX),
                    color = LocalColorsPalette.current.onSurface.copy(alpha = 0.6f),
                    fontSize = 14.sp
                )
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(dim.borderStroke)
                        .background(LocalColorsPalette.current.onSurface.copy(alpha = 0.2f))
                )
            }

            Spacer(modifier = Modifier.height(dim.medium))

            OutlinedButton(
                onClick = onGoogleSignIn,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(dim.buttonHeightLarge),
                shape = RoundedCornerShape(dim.roundingInput),
                border = BorderStroke(dim.borderStrokeThick, Color(0xFFE0E0E0)),
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = Color.White,
                    contentColor = Color(0xFF3C4043)
                ),
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = dim.cardElevation,
                    pressedElevation = dim.elevationMedium
                )
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    // Google "G" logo
                    Box(
                        modifier = Modifier
                            .size(dim.iconMedium)
                            .background(
                                Color.Transparent,
                                CircleShape
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "G",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF4285F4) // Google Blue
                        )
                    }
                    Spacer(modifier = Modifier.width(dim.small2X))
                    Text(
                        text = stringResource(Res.string.sign_in_with_google),
                        color = Color(0xFF3C4043),
                        fontWeight = FontWeight.Medium,
                        fontSize = 15.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(dim.small2X))

            Button(
                onClick = onVKSignIn,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(dim.buttonHeightLarge),
                shape = RoundedCornerShape(dim.roundingInput),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF4C75A3), // VK Blue background
                    contentColor = Color.White
                ),
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = dim.elevationSmall,
                    pressedElevation = dim.elevationLarge
                )
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    // VK "VK" logo
                    Box(
                        modifier = Modifier
                            .size(dim.iconMedium)
                            .background(
                                Color.White,
                                CircleShape
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "VK",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF4C75A3)
                        )
                    }
                    Spacer(modifier = Modifier.width(dim.small2X))
                    Text(
                        text = stringResource(Res.string.sign_in_with_vk),
                        color = Color.White,
                        fontWeight = FontWeight.Medium,
                        fontSize = 15.sp
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun ProfilePreview() {
    AuthenticatedProfileContent(
        state = ProfileUiState.preview(),
        viewModel = object : ProfileViewModel {
            override val state =
                com.arkivanov.decompose.value.MutableValue(ProfileUiState.preview())

            override fun updateFirstName(firstName: String) {}
            override fun updateLastName(lastName: String) {}
            override fun updateEmail(email: String) {}
            override fun updateAvatar(avatar: String?) {}
            override fun toggleEditing() {}
            override fun saveProfile() {}
            override fun loadProfile() {}
            override fun checkAuthStatus() {}
            override fun goToAuth() {}
            override fun signInWithGoogle() {}
            override fun signInWithVK() {}
        },
        onNavigateBack = {}
    )
}