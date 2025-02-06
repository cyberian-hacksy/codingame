package easy

import kotlin.math.round

//https://www.codingame.com/training/easy/card-counting-when-easily-distracted

fun main(args: Array<String>) {
    val streamOfConsciousness =
        "222.333.444.some distraction.555.5.678.678.678.678.another distraction.9999.TTTT.JJJJ.QQQQ.KKKK.AAAA"
    val bustThreshold = 4

    val validCards = setOf('A', '2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K')

    // A map to track the number of times each card has been seen.
    // Initially, count 0 for each valid card.
    val observedCounts = mutableMapOf<Char, Int>()
    for (card in validCards) {
        observedCounts[card] = 0
    }

    // Split the stream on periods.
    val tokens = streamOfConsciousness.split('.')
    for (token in tokens) {
        // Check if the token is a series of cards:
        // It must be non-empty and every character is one of the valid card abbreviations.
        if (token.isNotEmpty() && token.all { it in validCards }) {
            for (char in token) {
                observedCounts[char] = observedCounts[char]!! + 1
            }
        }
    }

    // In a standard deck, each card appears 4 times.
    // Calculate the total remaining cards and the number that are "favorable"
    // (i.e. with value < bustThreshold).
    var totalRemaining = 0
    var favorableRemaining = 0

    for (card in validCards) {
        val countSeen = observedCounts[card] ?: 0
        val remaining = 4 - countSeen
        totalRemaining += remaining
        if (getCardValue(card) < bustThreshold) {
            favorableRemaining += remaining
        }
    }

    // Calculate the chance as a percentage, rounding to the nearest whole number.
    // Guard against division by zero if somehow the deck is exhausted.
    val chance =
        if (totalRemaining == 0) 0
        else round(favorableRemaining * 100.0 / totalRemaining).toInt()

    println("${chance}%")
}

fun getCardValue(c: Char): Int {
    return when (c) {
        'A' -> 1
        'K', 'Q', 'J', 'T' -> 10
        else -> c.toString().toInt()  // For digits 2-9.
    }
}