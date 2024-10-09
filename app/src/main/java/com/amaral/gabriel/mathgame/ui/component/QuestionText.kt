package com.amaral.gabriel.mathgame.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.amaral.gabriel.mathgame.ui.theme.green

@Composable
fun QuestionText(
    modifier: Modifier = Modifier,
    text: String
) {

    Text(
        text = text,
        fontSize = 24.sp,
        color = Color.White,
        textAlign = TextAlign.Center,
        modifier = modifier
            .background(color = green)
            .size(width = 300.dp, height = 76.dp)
            .wrapContentHeight()
    )

}