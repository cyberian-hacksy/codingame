package easy

import java.util.*
import kotlin.math.pow

//https://www.codingame.com/training/easy/defibrillators

fun main(args: Array<String>) {
    val input = Scanner(System.`in`)
    val LON = input.next()
    val LAT = input.next()
    val N = input.nextInt()
    if (input.hasNextLine()) {
        input.nextLine()
    }

    var minDistance = Double.MAX_VALUE
    var result : String? = null

    for (i in 0 until N) {
        val DEFIB = input.nextLine()
        DEFIB.split(";").apply {
            val name = this[1]
            val longtitudeA = this[4].replace(',', '.').toDouble()
            val latitudeA = this[5].replace(',', '.').toDouble()
            val longtitudeB = LON.replace(',', '.').toDouble()
            val latitudeB = LAT.replace(',', '.').toDouble()
            val x = (longtitudeB - longtitudeA) * Math.cos((latitudeA + latitudeB) / 2)
            val y = (latitudeB - latitudeA)
            val distance = Math.sqrt(x.pow(2) + y.pow(2)) * 6371
            if (distance < minDistance) {
                minDistance = distance
                result = name
            }
        }
    }

    println(result)
}