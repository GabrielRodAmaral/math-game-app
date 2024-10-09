package com.amaral.gabriel.mathgame.screen

import android.app.Activity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.amaral.gabriel.mathgame.R
import com.amaral.gabriel.mathgame.component.ActionButton
import com.amaral.gabriel.mathgame.navigation.Router
import com.amaral.gabriel.mathgame.ui.theme.blue

@Composable
fun ResultScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    score: Int
) {

    val context = LocalContext.current as Activity

    Column(
        modifier = modifier
            .fillMaxSize()
            .paint(
                painter = painterResource(id = R.drawable.img_background_florest),
                contentScale = ContentScale.FillHeight
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Spacer(modifier = Modifier.size(60.dp))

        Text(
            text = "Parabéns!",
            fontSize = 24.sp,
            color = blue,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.size(30.dp))
        
        Text(
            text = "Sua pontuação: $score",
            fontSize = 20.sp,
            color = blue
        )

        Spacer(modifier = Modifier.size(100.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {

            ActionButton(
                text = "Jogar",
                onClick = { navController.popBackStack(route = Router.HomeScreen.route, inclusive = false) },
                enabled = true
            )

            ActionButton(
                text = "Sair",
                onClick = { context.finish() },
                enabled = true
            )

        }

    }
}

@Preview(showSystemUi = true)
@Composable
fun ResultScreenPreview(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    ResultScreen(navController = navController, score = 200)
}