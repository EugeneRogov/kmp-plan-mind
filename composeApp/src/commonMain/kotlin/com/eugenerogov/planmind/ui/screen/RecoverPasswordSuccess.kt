package com.eugenerogov.planmind.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.eugenerogov.planmind.ui.component.typography.BodyMedium
import com.eugenerogov.planmind.ui.theme.LocalColorsPalette
import com.eugenerogov.planmind.ui.theme.LocalDim
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun RecoverPasswordSuccessScreen() {
    val snackBarHostState = remember { SnackbarHostState() }

    RecoverPasswordSuccessContent(
        scaffoldState = snackBarHostState,
        onClickReturn = {
        }
    )
}

@Composable
private fun RecoverPasswordSuccessContent(
    scaffoldState: SnackbarHostState,
    onClickReturn: () -> Unit
) {
    Scaffold(
        containerColor = LocalColorsPalette.current.background,
        snackbarHost = { SnackbarHost(scaffoldState) }
    ) { innerPadding ->
        Column(
            modifier =
            Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(
                    horizontal = LocalDim.current.small
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
//            Image(
//                modifier = Modifier
//                    .size(72.dp),
//                painter = painterResource(R.drawable.success),
//                contentDescription = ""
//            )
            BodyMedium(
                modifier =
                Modifier
                    .padding(
                        top = LocalDim.current.mediumX
                    ),
                text = "",
                maxLines = 3,
                textAlign = TextAlign.Center
            )
//            Button(
//                modifier =
//                Modifier
//                    .padding(
//                        top = LocalDim.current.mediumX
//                    )
//                    .fillMaxWidth(),
//                onClick = {
//                    onClickReturn.invoke()
//                },
//                content = stringResource(R.string.return_to_main)
//            )
        }
    }
}

@Preview()
@Composable
fun RecoverPasswordSuccessPreview() {
//    PlanMindTheme {
        RecoverPasswordSuccessContent(
            scaffoldState = remember { SnackbarHostState() },
            onClickReturn = {}
        )
//    }
}
