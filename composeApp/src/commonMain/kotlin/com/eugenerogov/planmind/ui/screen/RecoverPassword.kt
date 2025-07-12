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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import com.eugenerogov.planmind.viewmodel.RecoverPasswordViewModel
import com.eugenerogov.planmind.viewmodel.RecoverPasswordUiState
import com.eugenerogov.planmind.ui.component.input.InputEmail
import com.eugenerogov.planmind.ui.component.AppLogo
import com.eugenerogov.planmind.ui.theme.LocalColorsPalette
import com.eugenerogov.planmind.ui.theme.LocalDim
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import planmind.composeapp.generated.resources.Res
import planmind.composeapp.generated.resources.email_hint
import org.koin.core.parameter.parametersOf
import org.koin.mp.KoinPlatformTools

object RecoverPasswordScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        RecoverPasswordScreenContent(
            onRecoverSuccess = { navigator.pop() },
            onBackToLogin = { navigator.pop() }
        )
    }
}

@Composable
fun RecoverPasswordScreenContent(
    onRecoverSuccess: () -> Unit,
    onBackToLogin: () -> Unit
) {
    val snackBarHostState = remember { SnackbarHostState() }

    val recoverPasswordComponent = remember {
        KoinPlatformTools.defaultContext().get().get<RecoverPasswordViewModel>(
            parameters = {
                parametersOf(
                    DefaultComponentContext(lifecycle = LifecycleRegistry()),
                    onRecoverSuccess,
                    onBackToLogin
                )
            }
        )
    }

    var state by remember { mutableStateOf(RecoverPasswordUiState()) }

    LaunchedEffect(recoverPasswordComponent) {
        recoverPasswordComponent.state.subscribe { newState ->
            state = newState
        }
    }

    LaunchedEffect(key1 = state.errorMessage) {
        if (state.errorMessage.isNotEmpty()) {
            snackBarHostState.showSnackbar(state.errorMessage)
        }
    }

    LaunchedEffect(key1 = state.successMessage) {
        if (state.successMessage.isNotEmpty()) {
            snackBarHostState.showSnackbar(state.successMessage)
        }
    }

    RecoverPasswordContent(
        state = state,
        component = recoverPasswordComponent
    )
}

@Composable
private fun RecoverPasswordContent(
    state: RecoverPasswordUiState,
    component: RecoverPasswordViewModel
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
            // App Logo
            AppLogo(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = dim.small, bottom = dim.smallX)
            )

            Spacer(modifier = Modifier.height(dim.small3X))

            // Description
            Text(
                text = "Введите email для получения инструкций по восстановлению пароля",
                fontSize = 16.sp,
                color = LocalColorsPalette.current.onSurface.copy(alpha = 0.7f),
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(dim.medium))

            // Email field
            InputEmail(
                hint = stringResource(Res.string.email_hint),
                modifier = Modifier.fillMaxWidth(),
                text = state.email,
                onValueChange = component::updateEmail,
                inputType = KeyboardType.Email,
                imeAction = ImeAction.Done,
                enabled = state.isEmailEnabled && !state.inProgress
            )

            Spacer(modifier = Modifier.height(dim.medium))

            // Recover button
            Button(
                onClick = component::onClickRecover,
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
                    text = if (state.inProgress) "Отправляем..." else "Восстановить пароль",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp
                )
            }

            Spacer(modifier = Modifier.height(dim.smallX))

            // Back to login button
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Вспомнили пароль? ")
                TextButton(
                    onClick = component::onClickBackToLogin,
                    enabled = !state.inProgress
                ) {
                    Text(text = "Войти")
                }
            }

            // Success message
            if (state.successMessage.isNotEmpty()) {
                Spacer(modifier = Modifier.height(dim.smallX))
                Text(
                    text = state.successMessage,
                    fontSize = 14.sp,
                    color = Color(0xFF4CAF50), // Green color for success
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Preview()
@Composable
fun RecoverPasswordPreview() {
    RecoverPasswordContent(
        state = RecoverPasswordUiState.preview(),
        component = object : RecoverPasswordViewModel {
            override val state =
                com.arkivanov.decompose.value.MutableValue(RecoverPasswordUiState.preview())

            override fun updateEmail(email: String) {}
            override fun updateDebugMenuExpanded(expanded: Boolean) {}
            override fun onClickRecover() {}
            override fun onClickBackToLogin() {}
        }
    )
}