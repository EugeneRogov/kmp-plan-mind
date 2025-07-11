package com.eugenerogov.planmind.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import com.eugenerogov.planmind.viewmodel.RegisterViewModel
import com.eugenerogov.planmind.viewmodel.RegisterUiState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.eugenerogov.planmind.ui.component.input.InputEmail
import com.eugenerogov.planmind.ui.component.input.InputField
import com.eugenerogov.planmind.ui.component.AppLogo
import com.eugenerogov.planmind.ui.theme.LocalColorsPalette
import com.eugenerogov.planmind.ui.theme.LocalDim
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import planmind.composeapp.generated.resources.Res
import planmind.composeapp.generated.resources.email_hint
import planmind.composeapp.generated.resources.username_label
import planmind.composeapp.generated.resources.username_placeholder
import planmind.composeapp.generated.resources.password_label
import planmind.composeapp.generated.resources.password_placeholder
import planmind.composeapp.generated.resources.confirm_password_label
import planmind.composeapp.generated.resources.confirm_password_placeholder
import planmind.composeapp.generated.resources.register_progress
import planmind.composeapp.generated.resources.register_title
import planmind.composeapp.generated.resources.already_have_account
import org.koin.core.parameter.parametersOf
import org.koin.mp.KoinPlatformTools

object RegisterScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        RegisterScreenContent(
            goToMain = { navigator.push(LoginScreen) },
            goToLogin = { navigator.pop() }
        )
    }
}

@Composable
fun RegisterScreenContent(
    goToMain: () -> Unit,
    goToLogin: () -> Unit
) {
    val snackBarHostState = remember { SnackbarHostState() }

    val registerComponent = remember {
        KoinPlatformTools.defaultContext().get().get<RegisterViewModel>(
            parameters = {
                parametersOf(
                    DefaultComponentContext(lifecycle = LifecycleRegistry()),
                    goToMain,
                    goToLogin
                )
            }
        )
    }

    var state by remember { mutableStateOf(RegisterUiState()) }

    LaunchedEffect(registerComponent) {
        registerComponent.state.subscribe { newState ->
            state = newState
        }
    }

    LaunchedEffect(key1 = state.errorMessage) {
        if (state.errorMessage.isNotEmpty()) {
            snackBarHostState.showSnackbar(state.errorMessage)
        }
    }

    RegisterContent(
        state = state,
        component = registerComponent
    )
}

@Composable
private fun RegisterContent(
    state: RegisterUiState,
    component: RegisterViewModel
) {
    val dim = LocalDim.current

    Scaffold(
        containerColor = LocalColorsPalette.current.background,
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(dim.smallX),
            verticalArrangement = Arrangement.Center
        ) {
            // Logo section
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AppLogo(
                    modifier = Modifier
                        .padding(bottom = dim.small3X)
                )
            }

            Spacer(modifier = Modifier.height(dim.medium))

            // Username field
            InputField(
                value = state.username,
                onValueChange = component::updateUsername,
                label = stringResource(Res.string.username_label),
                placeholder = stringResource(Res.string.username_placeholder),
                modifier = Modifier.fillMaxWidth(),
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next,
                enabled = state.isUsernameEnabled && !state.inProgress
            )

            Spacer(modifier = Modifier.height(dim.smallX))

            // Email field
            InputEmail(
                hint = stringResource(Res.string.email_hint),
                modifier = Modifier.fillMaxWidth(),
                text = state.email,
                onValueChange = component::updateEmail,
                inputType = KeyboardType.Email,
                imeAction = ImeAction.Next,
                enabled = state.isEmailEnabled && !state.inProgress
            )

            Spacer(modifier = Modifier.height(dim.smallX))

            // Password field
            InputField(
                value = state.password,
                onValueChange = component::updatePassword,
                label = stringResource(Res.string.password_label),
                placeholder = stringResource(Res.string.password_placeholder),
                modifier = Modifier.fillMaxWidth(),
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Next,
                enabled = state.isPasswordEnabled && !state.inProgress,
                isPassword = true
            )

            Spacer(modifier = Modifier.height(dim.smallX))

            // Confirm Password field
            InputField(
                value = state.confirmPassword,
                onValueChange = component::updateConfirmPassword,
                label = stringResource(Res.string.confirm_password_label),
                placeholder = stringResource(Res.string.confirm_password_placeholder),
                modifier = Modifier.fillMaxWidth(),
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done,
                enabled = state.isConfirmPasswordEnabled && !state.inProgress,
                isPassword = true
            )

            Spacer(modifier = Modifier.height(dim.smallX))


            Spacer(modifier = Modifier.height(dim.small))

            // Register button
            Button(
                onClick = component::onClickRegister,
                enabled = !state.inProgress,
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
                    text = if (state.inProgress) stringResource(Res.string.register_progress) else stringResource(
                        Res.string.register_title
                    ),
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp
                )
            }

            Spacer(modifier = Modifier.height(dim.smallX))

            // Back to login button
            TextButton(
                onClick = component::onClickBackToLogin,
                modifier = Modifier.fillMaxWidth(),
                enabled = !state.inProgress
            ) {
                Text(stringResource(Res.string.already_have_account))
            }
        }
    }
}

@Preview()
@Composable
fun RegisterPreview() {
    RegisterContent(
        state = RegisterUiState.preview(),
        component = object : RegisterViewModel {
            override val state =
                com.arkivanov.decompose.value.MutableValue(RegisterUiState.preview())

            override fun updateUsername(username: String) {}
            override fun updateEmail(email: String) {}
            override fun updatePassword(password: String) {}
            override fun updateConfirmPassword(confirmPassword: String) {}
            override fun updateDebugMenuExpanded(expanded: Boolean) {}
            override fun onClickRegister() {}
            override fun onClickBackToLogin() {}
        }
    )
}