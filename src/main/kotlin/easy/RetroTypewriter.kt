package easy

import java.util.*

//https://www.codingame.com/training/easy/retro-typewriter-art

fun main(args : Array<String>) {
    val input = Scanner(System.`in`)
    val T = input.nextLine()

    val result = T.split(" ").map {
        if (it == "nl") {
            return@map "\n"
        }
        val symbol: String
        val count: Int
        if (it.contains("sp|bS|sQ".toRegex())) {
            symbol = when (it.takeLast(2)) {
                "bS" -> "\\"
                "sQ" -> "\'"
                else -> " "
            }
            count = it.substring(0, it.length - 2).toInt()
        } else {
            symbol = it.last().toString()
            count = it.substring(0, it.length - 1).toInt()
        }
        return@map symbol.repeat(count)
    }.joinToString("")

    println(result)
}