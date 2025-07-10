package com.eugenerogov.planmind.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import com.eugenerogov.planmind.viewmodel.ProfileViewModel
import com.eugenerogov.planmind.viewmodel.ProfileUiState
import com.eugenerogov.planmind.ui.component.input.InputEmail
import com.eugenerogov.planmind.ui.theme.LocalColorsPalette
import com.eugenerogov.planmind.ui.theme.LocalDim
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.core.parameter.parametersOf
import org.koin.mp.KoinPlatformTools

object ProfileScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        ProfileScreenContent(
            onNavigateBack = { navigator.pop() }
        )
    }
}

@Composable
fun ProfileScreenContent(
    onNavigateBack: () -> Unit = {}
) {
    val snackBarHostState = remember { SnackbarHostState() }

    val profileViewModel = remember {
        KoinPlatformTools.defaultContext().get().get<ProfileViewModel>(
            parameters = {
                parametersOf(
                    DefaultComponentContext(lifecycle = LifecycleRegistry()),
                    { /* Profile saved callback */ }
                )
            }
        )
    }

    var state by remember { mutableStateOf(ProfileUiState()) }

    LaunchedEffect(profileViewModel) {
        profileViewModel.loadProfile()
        profileViewModel.state.subscribe { newState ->
            state = newState
        }
    }

    LaunchedEffect(key1 = state.errorMessage) {
        if (state.errorMessage.isNotEmpty()) {
            snackBarHostState.showSnackbar(state.errorMessage)
        }
    }

    ProfileContent(
        state = state,
        viewModel = profileViewModel
    )
}

@Composable
private fun ProfileContent(
    state: ProfileUiState,
    viewModel: ProfileViewModel
) {
    Scaffold(
        containerColor = LocalColorsPalette.current.background,
        snackbarHost = { SnackbarHost(hostState = remember { SnackbarHostState() }) },
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

@Preview
@Composable
fun ProfilePreview() {
    ProfileContent(
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
        }
    )
}