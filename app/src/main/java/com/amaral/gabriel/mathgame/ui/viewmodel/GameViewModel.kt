package com.amaral.gabriel.mathgame.ui.viewmodel

import android.os.CountDownTimer
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.amaral.gabriel.mathgame.generateQuestion
import com.amaral.gabriel.mathgame.ui.state.GameScreenUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.util.Locale

class GameViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(GameScreenUiState())
    val uiState get() = _uiState.asStateFlow()

    init {
        _uiState.update { current ->
            current.copy(
                onAnswerChange = { text ->
                    _uiState.update {
                        it.copy(answer = text)
                    }
                },

                onNewQuestionRequested = { category ->
                    val questionAndAnswer = generateQuestion(category)
                    timer.start()
                    _uiState.update {
                        it.copy(
                            question = questionAndAnswer.keys.first(),
                            correctAnswer = questionAndAnswer.values.first(),
                            answer = "",
                            isConfirmButtonEnabled = true

                        )
                    }
                },

                onConfirmButtonClicked = { context ->
                    if (_uiState.value.answer.isEmpty()) {
                        Toast.makeText(context, "Digite sua resposta", Toast.LENGTH_SHORT).show()
                    } else {
                        timer.cancel()

                        if (uiState.value.answer.toInt() == uiState.value.correctAnswer) {
                            _uiState.update { current ->
                                current.copy(
                                    score = current.score + 10,
                                    question = "Parabéns...",
                                    answer = "",
                                    isConfirmButtonEnabled = false
                                )
                            }
                        } else {
                            _uiState.update { current ->
                                current.copy(
                                    lifes = current.lifes - 1,
                                    question = "Desculpe, resposta errada.",
                                    isConfirmButtonEnabled = false
                                )
                            }
                        }
                    }
                },

                onNextButtonClicked = { context ->
                    timer.cancel()

                    if (_uiState.value.lifes == 0) {
                        Toast.makeText(context, "Fim de jogo", Toast.LENGTH_SHORT).show()
                        _uiState.update { current ->
                            current.copy(
                                navigateToResultScreen = true
                            )
                        }
                    } else {
                        _uiState.value.onNewQuestionRequested(_uiState.value.category)
                        timer.start()
                    }
                }
            )
        }
    }

    private val timer = object : CountDownTimer(_uiState.value.totalTime, 1000L) {
        override fun onTick(untilFinish: Long) {
            _uiState.update { current ->
                current.copy(
                    remainingTimeText = String.format(
                        Locale.getDefault(),
                        "%02d",
                        untilFinish / 1000
                    )
                )
            }
        }

        override fun onFinish() {
            cancel()
            _uiState.update { current ->
                current.copy(
                    question = "Desculpe o tempo acabou!",
                    lifes = _uiState.value.lifes - 1,
                    isConfirmButtonEnabled = false
                )
            }
        }

    }.start()

    fun setCategory(category: String) {
        _uiState.update { current ->
            current.copy(
                category = category
            )
        }
    }
}