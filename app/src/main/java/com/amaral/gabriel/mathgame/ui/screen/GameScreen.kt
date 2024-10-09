package com.amaral.gabriel.mathgame.ui.screen

import android.os.CountDownTimer
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.amaral.gabriel.mathgame.R
import com.amaral.gabriel.mathgame.component.ActionButton
import com.amaral.gabriel.mathgame.component.QuestionText
import com.amaral.gabriel.mathgame.component.TopAppBar
import com.amaral.gabriel.mathgame.generateQuestion
import com.amaral.gabriel.mathgame.navigation.Router
import com.amaral.gabriel.mathgame.ui.component.AnswerTextField
import com.amaral.gabriel.mathgame.ui.state.GameScreenUiState
import com.amaral.gabriel.mathgame.ui.theme.green
import com.amaral.gabriel.mathgame.ui.viewmodel.GameViewModel
import java.util.Locale

@Composable
fun GameScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    category: String,
    viewModel: GameViewModel = GameViewModel()
) {
    viewModel.setCategory(category)
    val state by viewModel.uiState.collectAsState()
    GameScreen(modifier = modifier, navController = navController, state = state)
}

@Composable
fun GameScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    state: GameScreenUiState = GameScreenUiState()
) {

    val context = LocalContext.current

    LaunchedEffect(Unit) {
        if (state.question.isBlank()) {
            state.onNewQuestionRequested(state.category)
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = when (state.category) {
                    "add" -> "Adição"
                    "sub" -> "Subtração"
                    "div" -> "Divisão"
                    else -> "Multiplicação"
                },
                containerColor = green,
                navIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            Icons.AutoMirrored.Rounded.ArrowBack,
                            contentDescription = "Voltar",
                            tint = Color.White
                        )
                    }
                }
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
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.size(20.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(text = "Vidas: ", fontSize = 16.sp, color = Color.White)

                Text(text = state.lifes.toString(), fontSize = 16.sp, color = Color.White)

                Text(text = "Pontos: ", fontSize = 16.sp, color = Color.White)

                Text(text = state.score.toString(), fontSize = 16.sp, color = Color.White)

                Text(text = "Tempo restante: ", fontSize = 16.sp, color = Color.White)

                Text(text = state.remainingTimeText, fontSize = 16.sp, color = Color.White)
            }

            Spacer(modifier = Modifier.size(30.dp))

            QuestionText(text = state.question)

            Spacer(modifier = Modifier.size(16.dp))

            AnswerTextField(text = state.answer, onTextChange = state.onAnswerChange)

            Spacer(modifier = Modifier.size(22.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                ActionButton(
                    text = "Confirmar",
                    onClick = { state.onConfirmButtonClicked(context) },
                    enabled = state.isConfirmButtonEnabled
                )

                ActionButton(
                    text = "Próxima",
                    onClick = { state.onNextButtonClicked(context) },
                    enabled = true
                )
            }

            LaunchedEffect(key1 = state.navigateToResultScreen) {
                if (state.navigateToResultScreen) {
                    navController.navigate(Router.ResultScreen.create(state.score)) {
                        popUpTo(route = Router.HomeScreen.route) { inclusive = false }
                    }
                }
            }
        }

    }

}

//@Preview(showSystemUi = true)
//@Composable
//fun GameScreenPreview(modifier: Modifier = Modifier) {
//    val navController = rememberNavController()
//    Scaffold {
//        GameScreen(
//            modifier = Modifier.padding(it),
//            navController = navController,
//            category = "add"
//        )
//    }
//}