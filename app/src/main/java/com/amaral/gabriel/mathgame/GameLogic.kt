package com.amaral.gabriel.mathgame

import kotlin.random.Random

fun generateQuestion(selectedCategory: String): Map<String, Int> {
    var n1 = Random.nextInt(0, 100)
    var n2 = Random.nextInt(0, 100)

    val question: String
    val correctAnswer: Int

    when (selectedCategory){
        "add" -> {
            question = "$n1 + $n2?"
            correctAnswer = n1 + n2
        }
        "sub" -> {
            if (n1 >= n2) {
                question = "$n1 - $n2?"
                correctAnswer = n1 - n2
            } else {
                question = "$n2 - $n1?"
                correctAnswer = n2 - n1
            }
        }
        "multi" -> {
            n1 = Random.nextInt(0, 21)
            n2 = Random.nextInt(0, 21)

            question = "$n1 x $n2?"
            correctAnswer = n1 * n2
        }
        else -> {
            if (n1 == 0 || n2 == 0) {
                question = "0 / 1"
                correctAnswer = 0
            } else if (n1 >= n2) {
                val newBigNumber = n1 - (n1%n2)
                question = "$newBigNumber / $n2?"
                correctAnswer = newBigNumber / n2
            } else {
                val newBigNumber = n2 - (n2%n1)
                question = "$newBigNumber / $n1?"
                correctAnswer = newBigNumber / n1
            }
        }
    }

    val gameResult = mapOf(question to correctAnswer)
    return gameResult
}