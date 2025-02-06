package easy

import java.util.*

//https://www.codingame.com/training/easy/the-descent

fun main(args : Array<String>) {
    val input = Scanner(System.`in`)

    // game loop
    while (true) {
        var mountainId = 0
        var highestMountainHeight = 0
        for (i in 0 until 8) {
            val mountainH = input.nextInt() // represents the height of one mountain.
            if (mountainH > highestMountainHeight) {
                highestMountainHeight = mountainH
                mountainId = i
            }
        }

        println(mountainId) // The index of the mountain to fire on.
    }
}