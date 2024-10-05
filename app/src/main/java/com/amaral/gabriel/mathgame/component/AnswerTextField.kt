package com.amaral.gabriel.mathgame.component

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.amaral.gabriel.mathgame.ui.theme.green

@Composable
fun AnswerTextField(
    modifier: Modifier = Modifier,
    text: MutableState<String>
) {

    TextField(
        value = text.value,
        onValueChange = { text.value = it },
        label = { Text(text = "Digite sua resposta") },
        colors = TextFieldDefaults.colors(
            unfocusedLabelColor = Color.White,
            focusedLabelColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedContainerColor = green,
            focusedContainerColor = green,
            focusedTextColor = Color.White,
            unfocusedTextColor = Color.White,
            cursorColor = Color.White
        ),
        modifier = modifier
            .size(width = 300.dp, height = 76.dp),
        textStyle = TextStyle(fontSize = 24.sp, textAlign = TextAlign.Center),
        shape = RoundedCornerShape(5.dp),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
    )

}