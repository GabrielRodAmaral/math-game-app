package com.amaral.gabriel.mathgame.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.amaral.gabriel.mathgame.R
import com.amaral.gabriel.mathgame.component.TopAppBar
import com.amaral.gabriel.mathgame.navigation.Router
import com.amaral.gabriel.mathgame.ui.theme.green

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navController: NavController
) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = "Math game",
                containerColor = green
            )
        }
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(it)
                .paint(
                    painter = painterResource(id = R.drawable.img_background_florest),
                    contentScale = ContentScale.FillHeight
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {

            Button(
                onClick = {
                    navController.navigate(Router.GameScreen.create("add"))
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = green
                ),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.size(200.dp, 64.dp)
            ) {
                Text(
                    text = "Adição",
                    color = Color.White,
                    fontSize = 24.sp
                )
            }

            Button(
                onClick = {
                    navController.navigate(Router.GameScreen.create("sub"))
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = green
                ),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.size(200.dp, 64.dp)
            ) {
                Text(
                    text = "Subtração",
                    color = Color.White,
                    fontSize = 24.sp
                )
            }

            Button(
                onClick = {
                    navController.navigate(Router.GameScreen.create("multi"))
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = green
                ),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.size(200.dp, 64.dp)
            ) {
                Text(
                    text = "Multiplicação",
                    color = Color.White,
                    fontSize = 24.sp
                )
            }

            Button(
                onClick = {
                    navController.navigate(Router.GameScreen.create("div"))
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = green
                ),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.size(200.dp, 64.dp)
            ) {
                Text(
                    text = "Divisão",
                    color = Color.White,
                    fontSize = 24.sp
                )
            }

        }
    }



}

@Preview(showSystemUi = true)
@Composable
fun FirstScreenPreview(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    Scaffold {
        HomeScreen(
            modifier = Modifier.padding(it),
            navController = navController
        )
    }
}