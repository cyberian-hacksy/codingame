package easy

import java.util.*
import kotlin.collections.ArrayList

//https://www.codingame.com/training/easy/horse-racing-duals

fun main(args : Array<String>) {
    val input = Scanner(System.`in`)
    val N = input.nextInt()
    val horses = ArrayList<Int>()
    for (i in 0 until N) {
        val pi = input.nextInt()
        horses.add(pi)
    }

    val result = horses.sorted().zipWithNext { cur, next -> next - cur }.minOrNull()
    println(result)
}