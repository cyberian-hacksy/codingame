package easy

import java.util.*
import kotlin.collections.ArrayList

//https://www.codingame.com/training/easy/ascii-art

fun main(args : Array<String>) {
    val input = Scanner(System.`in`)
    val L = input.nextInt()
    val H = input.nextInt()
    if (input.hasNextLine()) {
        input.nextLine()
    }
    val T = input.nextLine()
    var result = ""

    val keys = ('A'..'Z') + '?'
    val letters = keys.associateWith { ArrayList<String>() }

    for (i in 0 until H) {
        val ROW = input.nextLine()
        ROW.chunked(L).run {
            keys.forEachIndexed { index, c -> letters[c]?.add(this[index]) }
        }
    }

    val preparedT = T.replace("[^A-Za-z]".toRegex(), "?").uppercase()
    repeat(H) { i ->
        preparedT.forEachIndexed { index, c ->  result += letters[c]!![i]}
        result += "\n"
    }

    println(result)
}