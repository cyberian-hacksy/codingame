package easy

import java.util.*
import kotlin.collections.HashMap

//https://www.codingame.com/training/easy/mime-type

fun main(args : Array<String>) {
    val input = Scanner(System.`in`)
    val N = input.nextInt() // Number of elements which make up the association table.
    val Q = input.nextInt() // Number Q of file names to be analyzed.
    val typeMap = HashMap<String, String>().withDefault { "UNKNOWN" }
    for (i in 0 until N) {
        val EXT = input.next() // file extension
        val MT = input.next() // MIME type.
        typeMap[EXT.lowercase()] = MT
    }
    input.nextLine()
    for (i in 0 until Q) {
        val FNAME = input.nextLine() // One file name per line.
        val extension = FNAME.substringAfterLast('.', "")
        println(typeMap.getValue(extension.lowercase()))
    }
}