package com.eugenerogov.planmind.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import com.eugenerogov.planmind.ui.component.input.InputEmail
import com.eugenerogov.planmind.ui.component.input.InputField
import com.eugenerogov.planmind.ui.component.AppLogo
import com.eugenerogov.planmind.ui.theme.LocalColorsPalette
import com.eugenerogov.planmind.ui.theme.LocalDim
import com.eugenerogov.planmind.viewmodel.LoginUiState
import com.eugenerogov.planmind.viewmodel.LoginViewModel
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.core.parameter.parametersOf
import org.koin.mp.KoinPlatformTools
import planmind.composeapp.generated.resources.Res
import planmind.composeapp.generated.resources.email_hint

object LoginScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        LoginScreenContent(
            goToMain = { navigator.push(LoginScreen) },
            goToRegister = { navigator.push(RegisterScreen) },
            goToRecoverPassword = { navigator.push(RecoverPasswordScreen) }
        )
    }
}

@Composable
fun LoginScreenContent(
    goToMain: () -> Unit,
    goToRegister: () -> Unit,
    goToRecoverPassword: () -> Unit
) {
    val snackBarHostState = remember { SnackbarHostState() }

    val loginComponent = remember {
        KoinPlatformTools.defaultContext().get().get<LoginViewModel>(
            parameters = {
                parametersOf(
                    DefaultComponentContext(lifecycle = LifecycleRegistry()),
                    goToMain,
                    goToRecoverPassword,
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
        goToRegister = goToRegister,
        goToRecoverPassword = goToRecoverPassword
    )
}

@Composable
private fun LoginContent(
    state: LoginUiState,
    component: LoginViewModel,
    goToRegister: () -> Unit,
    goToRecoverPassword: () -> Unit
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

            Spacer(modifier = Modifier.height(dim.avatar))

            InputEmail(
                hint = stringResource(Res.string.email_hint),
                modifier = Modifier.fillMaxWidth(),
                text = state.login,
                onValueChange = component::updateLogin,
                inputType = KeyboardType.Email,
                imeAction = ImeAction.Next,
                enabled = state.isLoginEnabled && !state.inProgress
            )

            Spacer(modifier = Modifier.height(dim.smallX))

            // Password field using modern InputField
            InputField(
                label = "Пароль",
                placeholder = "Введите пароль",
                value = state.password,
                onValueChange = component::updatePassword,
                modifier = Modifier.fillMaxWidth(),
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done,
                enabled = state.isPasswordEnabled && !state.inProgress,
                isPassword = true
            )

            Spacer(modifier = Modifier.height(dim.small3X))

            // "Напомнить пароль?" clickable text
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                TextButton(
                    onClick = goToRecoverPassword,
                    enabled = !state.inProgress
                ) {
                    Text(
                        text = "Напомнить пароль?",
                        fontSize = 14.sp,
                        color = LocalColorsPalette.current.primary
                    )
                }
            }

            Spacer(modifier = Modifier.height(dim.smallX))

            // Login button (updated to match "Войти в аккаунт" modern style)
            Button(
                onClick = component::onClickLogin,
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
                    text = if (state.inProgress) "Входим..." else "Войти",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp
                )
            }

            // "Впервые? Зарегистрироваться" below login button
            Spacer(modifier = Modifier.height(dim.small3X))
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
        goToRegister = {},
        goToRecoverPassword = {}
    )
}
