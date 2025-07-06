package com.eugenerogov.planmind.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.eugenerogov.planmind.ui.component.button.ButtonLarge
import com.eugenerogov.planmind.ui.theme.LocalColorsPalette
import org.jetbrains.compose.ui.tooling.preview.Preview

object LoginScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        LoginScreenContent(
            goToMain = { navigator.push(LoginScreen) }
        )
    }
}

@Composable
fun LoginScreenContent(
    goToMain: () -> Unit
) {
    val scope = rememberCoroutineScope()
    val snackBarHostState = remember { SnackbarHostState() }

    LaunchedEffect(
        key1 = true
    ) {

    }

    LoginContent(
        scaffoldState = snackBarHostState,
        updateLogin = {

        },
        updatePassword = {

        },
        updateLoginIn = {

        },
        updateDebugMenuExpanded = {

        },
        onClickLogin = {

        },
        onClickForgotPassword = {

        },
        onClickNetworkSettings = {

        }
    )
}

@Composable
private fun LoginContent(
    scaffoldState: SnackbarHostState,

    updateLogin: (String) -> Unit,
    updatePassword: (String) -> Unit,
    updateLoginIn: (Boolean) -> Unit,
    updateDebugMenuExpanded: (Boolean) -> Unit,
    onClickLogin: () -> Unit,
    onClickForgotPassword: () -> Unit,
    onClickNetworkSettings: () -> Unit
) {
    Scaffold(
        containerColor = LocalColorsPalette.current.background,
    ) { innerPadding ->
    Column(
        modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        // Login field
        OutlinedTextField(
            value = "",  // Assume 'login' is passed as a parameter to LoginContent
            onValueChange = updateLogin,
            label = { Text("Login") },
            placeholder = { Text("Enter your login") },
            modifier = Modifier.fillMaxWidth()
        )

        // Password field
        OutlinedTextField(
            value = "",  // Assume 'password' is passed as a parameter to LoginContent
            onValueChange = updatePassword,
            label = { Text("Password") },
            placeholder = { Text("Enter your password") },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Login button
        ButtonLarge(
            onClick = onClickLogin,
            text = "Login"
        )
    }}
}

@Preview()
@Composable
fun LoginPreview() {
    LoginContent(
        scaffoldState = remember { SnackbarHostState() },
        updateLogin = {},
        updatePassword = {},
        updateLoginIn = {},
        updateDebugMenuExpanded = {},
        onClickLogin = {},
        onClickForgotPassword = {},
        onClickNetworkSettings = {}
    )
}
