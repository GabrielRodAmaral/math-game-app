package com.amaral.gabriel.mathgame.ui.state

import android.content.Context

data class GameScreenUiState(
    val category: String = "",
    val lifes: Int = 3,
    val score: Int = 0,
    val remainingTimeText: String = "30",
    val question: String = "",
    val correctAnswer: Int = 0,
    val answer: String = "",
    val isConfirmButtonEnabled: Boolean = true,
    val totalTime: Long = 30000L,
    val navigateToResultScreen: Boolean = false,
    val onAnswerChange: (String) -> Unit = {},
    val onConfirmButtonClicked: (context: Context) -> Unit = {},
    val onNextButtonClicked: (context: Context) -> Unit = {},
    val onNewQuestionRequested: (String) -> Unit = {}
)