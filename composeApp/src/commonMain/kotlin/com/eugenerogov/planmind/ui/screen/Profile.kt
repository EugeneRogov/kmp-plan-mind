package com.eugenerogov.planmind.ui.screen

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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
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
            onGoToAuth = onNavigateToAuth
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
                    text = if (state.isEditing) "Save" else "Edit",
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
                    .padding(LocalDim.current.smallX),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(LocalDim.current.smallX)
            ) {
                Spacer(modifier = Modifier.height(LocalDim.current.medium))

                // Avatar
                Box(
                    modifier = Modifier
                        .size(120.dp)
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
                    OutlinedTextField(
                        value = state.firstName,
                        onValueChange = viewModel::updateFirstName,
                        label = { Text("First Name") },
                        placeholder = { Text("Enter your first name") },
                        modifier = Modifier.fillMaxWidth(),
                        enabled = !state.isSaving
                    )

                    // Last Name
                    OutlinedTextField(
                        value = state.lastName,
                        onValueChange = viewModel::updateLastName,
                        label = { Text("Last Name") },
                        placeholder = { Text("Enter your last name") },
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
                        hint = "Email",
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
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(16.dp)
                        )
                        Text(
                            text = "Saving...",
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
    onGoToAuth: () -> Unit
) {
    Scaffold(
        containerColor = LocalColorsPalette.current.background
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(LocalDim.current.smallX),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "👤",
                fontSize = 80.sp,
                color = LocalColorsPalette.current.onSurface
            )

            Spacer(modifier = Modifier.height(LocalDim.current.medium))

            Text(
                text = "Войдите в свой аккаунт",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = LocalColorsPalette.current.onSurface
            )

            Spacer(modifier = Modifier.height(LocalDim.current.small))

            Text(
                text = "Для доступа к профилю необходимо авторизоваться",
                fontSize = 16.sp,
                color = LocalColorsPalette.current.onSurface.copy(alpha = 0.7f),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(LocalDim.current.medium))

            Button(
                onClick = onGoToAuth,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Go to auth")
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
        },
        onNavigateBack = {}
    )
}