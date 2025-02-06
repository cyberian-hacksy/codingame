package easy

import java.util.*
import kotlin.math.absoluteValue

//https://www.codingame.com/training/easy/temperatures

fun main(args : Array<String>) {
    val input = Scanner(System.`in`)
    val n = input.nextInt() // the number of temperatures to analyse
    var result = if (n == 0) 0 else Integer.MAX_VALUE
    for (i in 0 until n) {
        val t = input.nextInt() // a temperature expressed as an integer ranging from -273 to 5526
        result = if (t == result || t.absoluteValue < result.absoluteValue) t
        else if (t.absoluteValue == result.absoluteValue) result.absoluteValue
        else result
    }

    println(result)
}