package com.amaral.gabriel.mathgame.screen

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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.amaral.gabriel.mathgame.R
import com.amaral.gabriel.mathgame.component.AnswerTextField
import com.amaral.gabriel.mathgame.component.ActionButton
import com.amaral.gabriel.mathgame.component.QuestionText
import com.amaral.gabriel.mathgame.component.TopAppBar
import com.amaral.gabriel.mathgame.generateQuestion
import com.amaral.gabriel.mathgame.ui.theme.green
import java.util.Locale

@Composable
fun GameScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    category: String
) {

    val context = LocalContext.current

    val life = remember {
        mutableStateOf(3)
    }

    val score = remember {
        mutableStateOf(0)
    }

    val remainingTime = remember {
        mutableStateOf("30")
    }

    val question = remember {
        mutableStateOf("")
    }

    val answer = remember {
        mutableStateOf("")
    }

    val isEnabled = remember {
        mutableStateOf(true)
    }

    val correctAnswer = remember {
        mutableStateOf(0)
    }

    val totalTime = remember {
        mutableStateOf(30000L)
    }

    val timer = remember {
        mutableStateOf(
            object: CountDownTimer(totalTime.value, 1000L) {
                override fun onTick(untilFinish: Long) {
                    remainingTime.value = String.format(Locale.getDefault(), "%02d", untilFinish/1000)
                }

                override fun onFinish() {
                    cancel()
                    question.value = "Desculpe, o tempo acabou!"
                    life.value -= 1
                    isEnabled.value = false
                }

            }.start()
        )
    }

    LaunchedEffect(key1 = "math") {
        val questionAndAnswer = generateQuestion(category)
        question.value = questionAndAnswer.keys.first()
        correctAnswer.value = questionAndAnswer.values.first()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = when (category) {
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

                Text(text = life.value.toString(), fontSize = 16.sp, color = Color.White)

                Text(text = "Pontos: ", fontSize = 16.sp, color = Color.White)

                Text(text = score.value.toString(), fontSize = 16.sp, color = Color.White)

                Text(text = "Tempo restante: ", fontSize = 16.sp, color = Color.White)

                Text(text = remainingTime.value, fontSize = 16.sp, color = Color.White)
            }

            Spacer(modifier = Modifier.size(30.dp))

            QuestionText(text = question.value)

            Spacer(modifier = Modifier.size(16.dp))

            AnswerTextField(text = answer)

            Spacer(modifier = Modifier.size(22.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                ActionButton(
                    text = "Confirmar",
                    onClick = {
                        if (answer.value.isEmpty()) {
                            Toast.makeText(context, "Digite sua resposta", Toast.LENGTH_SHORT).show()
                        } else {
                            timer.value.cancel()

                            isEnabled.value = false

                            if (answer.value.toInt() == correctAnswer.value) {
                                score.value += 10
                                question.value = "Parabéns..."
                                answer.value = ""
                            } else {
                                life.value -= 1
                                question.value = "Desculpe, resposta errada."
                            }
                        }
                    },
                    enabled = isEnabled.value
                )

                ActionButton(
                    text = "Próxima",
                    onClick = {
                        timer.value.cancel()

                        if (life.value == 0) {
                            Toast.makeText(context, "Fim de jogo", Toast.LENGTH_SHORT).show()
                            // TODO: Result page
                        } else {
                            val newQuestion = generateQuestion(category)
                            question.value = newQuestion.keys.first()
                            correctAnswer.value = newQuestion.values.first()

                            answer.value = ""

                            isEnabled.value = true
                            timer.value.start()
                        }
                    },
                    enabled = true
                )
            }

        }

    }

}

@Preview(showSystemUi = true)
@Composable
fun GameScreenPreview(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    Scaffold {
        GameScreen(
            modifier = Modifier.padding(it),
            navController = navController,
            category = "add"
        )
    }
}