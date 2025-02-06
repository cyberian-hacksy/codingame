package easy

import java.util.*

//https://www.codingame.com/training/easy/merlins-magic-square

fun main(args : Array<String>) {
    val input = Scanner(System.`in`)
    val row1 = input.nextLine()
    val row2 = input.nextLine()
    val row3 = input.nextLine()
    var state = convert(listOf(row1, row2, row3).joinToString("\r\n"))
    val allButtonsPressed = input.nextLine()

    allButtonsPressed.forEach { state = applyCommand(it.digitToInt(), state) }

    for (i in 1..9) {
        if (convert(applyCommand(i, state)) == """
        * * *
        * ~ *
        * * *
    """.trimIndent()
        ) {
            println(i)
            return
        }
    }
}

fun applyCommand(command: Int, state: List<MutableList<Boolean>>): List<MutableList<Boolean>> {
    val result = state.map { it.toMutableList() }
    when (command) {
        1 -> invert(result, listOf(Pair(0, 0), Pair(0, 1), Pair(1, 0), Pair(1, 1)))
        3 -> invert(result, listOf(Pair(0, 1), Pair(0, 2), Pair(1, 1), Pair(1, 2)))
        7 -> invert(result, listOf(Pair(1, 0), Pair(1, 1), Pair(2, 0), Pair(2, 1)))
        9 -> invert(result, listOf(Pair(1, 1), Pair(1, 2), Pair(2, 1), Pair(2, 2)))
        2 -> invert(result, listOf(Pair(0, 0), Pair(0, 1), Pair(0, 2)))
        4 -> invert(result, listOf(Pair(0, 0), Pair(1, 0), Pair(2, 0)))
        6 -> invert(result, listOf(Pair(0, 2), Pair(1, 2), Pair(2, 2)))
        8 -> invert(result, listOf(Pair(2, 0), Pair(2, 1), Pair(2, 2)))
        5 -> invert(result, listOf(Pair(0, 1), Pair(1, 0), Pair(1, 1), Pair(1, 2), Pair(2, 1)))
    }
    return result
}

fun invert(state: List<MutableList<Boolean>>, coordinates: List<Pair<Int, Int>>) {
    coordinates.forEach {
        state[it.first][it.second] = !state[it.first][it.second]
    }
}

fun convert(state: List<MutableList<Boolean>>): String {
    return state.map { it.map { c -> if (c) "*" else "~" }.joinToString(" ") }.joinToString("\n")
}

fun convert(state: String): List<MutableList<Boolean>> {
    return state.lines().map { it.split(" ").map { it == "*" }.toMutableList() }
}