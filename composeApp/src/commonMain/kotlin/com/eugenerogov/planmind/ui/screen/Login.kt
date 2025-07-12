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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.shape.RoundedCornerShape
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import com.eugenerogov.planmind.viewmodel.LoginViewModel
import com.eugenerogov.planmind.viewmodel.LoginUiState
import com.eugenerogov.planmind.ui.component.input.InputEmail
import com.eugenerogov.planmind.ui.theme.LocalColorsPalette
import com.eugenerogov.planmind.ui.theme.LocalDim
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import planmind.composeapp.generated.resources.Res
import planmind.composeapp.generated.resources.email_hint
import org.koin.core.parameter.parametersOf
import org.koin.mp.KoinPlatformTools

object LoginScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        LoginScreenContent(
            goToMain = { navigator.push(LoginScreen) },
            goToRegister = { navigator.push(RegisterScreen) }
        )
    }
}

@Composable
fun LoginScreenContent(
    goToMain: () -> Unit,
    goToRegister: () -> Unit
) {
    val snackBarHostState = remember { SnackbarHostState() }

    val loginComponent = remember {
        KoinPlatformTools.defaultContext().get().get<LoginViewModel>(
            parameters = {
                parametersOf(
                    DefaultComponentContext(lifecycle = LifecycleRegistry()),
                    goToMain,
                    { /* Navigate to forgot password */ },
                    { /* Navigate to network settings */ }
                )
            }
        )
    }

    var state by remember { mutableStateOf(LoginUiState()) }

    LaunchedEffect(loginComponent) {
        loginComponent.state.subscribe { newState ->
            state = newState
        }
    }

    LaunchedEffect(key1 = state.errorMessage) {
        if (state.errorMessage.isNotEmpty()) {
            snackBarHostState.showSnackbar(state.errorMessage)
        }
    }

    LoginContent(
        state = state,
        component = loginComponent,
        goToRegister = goToRegister
    )
}

@Composable
private fun LoginContent(
    state: LoginUiState,
    component: LoginViewModel,
    goToRegister: () -> Unit
) {
    Scaffold(
        containerColor = LocalColorsPalette.current.background,
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(LocalDim.current.smallX),
            verticalArrangement = Arrangement.Center
        ) {
            InputEmail(
                hint = stringResource(Res.string.email_hint),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = LocalDim.current.medium,
                        bottom = LocalDim.current.small4X
                    ),
                text = state.login,
                onValueChange = component::updateLogin,
                inputType = KeyboardType.Email,
                imeAction = ImeAction.Next,
                enabled = state.isLoginEnabled && !state.inProgress
            )

            // Password field
            OutlinedTextField(
                value = state.password,
                onValueChange = component::updatePassword,
                label = { Text("Password") },
                placeholder = { Text("Enter your password") },
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                enabled = state.isPasswordEnabled && !state.inProgress
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Login button (updated to match "Войти в аккаунт" modern style)
            Button(
                onClick = component::onClickLogin,
                enabled = !state.inProgress,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = LocalColorsPalette.current.primary,
                    contentColor = LocalColorsPalette.current.onPrimary
                ),
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 2.dp,
                    pressedElevation = 8.dp
                )
            ) {
                Text(
                    text = if (state.inProgress) "Входим..." else "Войти",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp
                )
            }

            // "Впервые? Зарегистрироваться" below login button
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Впервые? ")
                TextButton(onClick = goToRegister) {
                    Text(text = "Зарегистрироваться")
                }
            }
        }
    }
}

@Preview()
@Composable
fun LoginPreview() {
    LoginContent(
        state = LoginUiState.preview(),
        component = object : LoginViewModel {
            override val state = com.arkivanov.decompose.value.MutableValue(LoginUiState.preview())

            override fun updateLogin(login: String) {}
            override fun updatePassword(password: String) {}
            override fun updateLoginIn(isLoginIn: Boolean) {}
            override fun updateDebugMenuExpanded(expanded: Boolean) {}
            override fun onClickLogin() {}
            override fun onClickForgotPassword() {}
            override fun onClickNetworkSettings() {}
        },
        goToRegister = {}
    )
}
