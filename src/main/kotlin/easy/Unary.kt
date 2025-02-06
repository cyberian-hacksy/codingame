package easy

import java.util.*

//https://www.codingame.com/training/easy/unary

fun main(args : Array<String>) {
    val input = Scanner(System.`in`)
    val MESSAGE = input.nextLine()
    var result = ""
    val binaryMessage = MESSAGE.toByteArray()
        .map { String.format("%7s", Integer.toBinaryString(it.toInt().and(0xFF))).replace(" ", "0") }
        .joinToString("")
    """(.)\1*""".toRegex()
        .findAll(binaryMessage)
        .map { it.value }
        .forEach {
            val firstBlock = if (it[0] == '1') " 0 " else " 00 "
            val secondBlock = "0".repeat(it.length)
            result += firstBlock + secondBlock
        }
    println(result.trim())
}