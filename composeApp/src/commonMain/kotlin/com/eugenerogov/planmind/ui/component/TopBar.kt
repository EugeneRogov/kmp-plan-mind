package com.eugenerogov.planmind.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.eugenerogov.planmind.ui.component.typography.TitleMedium
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    icon: Painter,
    actionIcon: Painter? = null,
    text: String,
    iconSize: Dp = 24.dp,
    containerColor: Color = Color.Companion.Transparent,
    onClickBack: () -> Unit,
    onClickMenu: () -> Unit = {},
    visibleActions: Boolean = false
) {
    TopAppBar(
        modifier = modifier.fillMaxWidth(),
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = containerColor
        ),
        navigationIcon = {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(30.dp))
                    .clickable {
                        onClickBack.invoke()
                    }
            ) {
                Image(
                    modifier = Modifier
                        .padding(8.dp)
                        .size(iconSize),
                    painter = icon,
                    contentDescription = ""
                )
            }
        },
        title = {
            TitleMedium(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 40.dp),
                text = text,
                textAlign = TextAlign.Center,
                maxLines = 2
            )
        },
        actions = {
            if (visibleActions) {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(30.dp))
                        .clickable {
                            onClickMenu.invoke()
                        }
                ) {
//                    Image(
//                        modifier = Modifier
//                            .padding(8.dp)
//                            .size(iconSize),
//                        painter = actionIcon ?: painterResource(R.drawable.timer),
//                        contentDescription = ""
//                    )
                }
            }
        }
    )
}


@Preview()
@Composable
fun TopBarPreview() {
//    TopBar(
//        icon = painterResource(R.drawable.timer),
//        actionIcon = painterResource(R.drawable.timer),
//        text = "Добрый день, Евгений!",
//        onClickBack = {},
//        onClickMenu = {}
//    )
}
