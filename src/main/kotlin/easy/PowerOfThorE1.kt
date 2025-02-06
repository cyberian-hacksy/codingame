package easy

import java.util.*

//https://www.codingame.com/training/easy/power-of-thor-episode-1

fun main(args : Array<String>) {
    val input = Scanner(System.`in`)
    val lightX = input.nextInt() // the X position of the light of power
    val lightY = input.nextInt() // the Y position of the light of power
    val initialTx = input.nextInt() // Thor's starting X position
    val initialTy = input.nextInt() // Thor's starting Y position
    var currentTx = initialTx
    var currentTy = initialTy

    // game loop
    while (true) {
        val remainingTurns = input.nextInt() // The remaining amount of turns Thor can move. Do not remove this line.

        val x = lightX.compareTo(currentTx)
        val y = lightY.compareTo(currentTy)
        currentTx += x
        currentTy += y

        val xDirection = when (x) {
            -1 -> "W"
            1 -> "E"
            else -> ""
        }
        val yDirection = when (y) {
            -1 -> "N"
            1 -> "S"
            else -> ""
        }

        // A single line providing the move to be made: N NE E SE S SW W or NW
        println(yDirection + xDirection)
    }
}