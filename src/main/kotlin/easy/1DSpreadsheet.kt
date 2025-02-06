package easy

import java.util.*
import kotlin.collections.ArrayList

//https://www.codingame.com/training/easy/1d-spreadsheet

@OptIn(ExperimentalStdlibApi::class)
fun main(args : Array<String>) {
    val input = Scanner(System.`in`)
    val N = input.nextInt()


    val cells: MutableList<Int?> = buildList {
        repeat(N) { add(null) }
    }.toMutableList()
    val operations = ArrayList<Operation>()

    for (i in 0 until N) {
        val operation = input.next()
        val arg1 = input.next()
        val arg2 = input.next()
        operations.add(Operation(operation, arg1, arg2))
    }
    for (i in 0 until N) {

        println(calculate(operations[i], cells, operations))
    }
}

data class Operation(val type: String, val arg1: String, val arg2: String)

fun calculate(o: Operation, cells: MutableList<Int?>, operations: List<Operation>): Int {
    val arg1 = parseArg(o.arg1, cells, operations)
    val arg2 = parseArg(o.arg2, cells, operations)
    return when (o.type) {
        "VALUE" -> arg1
        "ADD" -> arg1 + arg2
        "SUB" -> arg1 - arg2
        "MULT" -> arg1 * arg2
        else -> 0
    }
}

fun parseArg(arg: String, cells: MutableList<Int?>, operations: List<Operation>): Int {
    return if (arg.startsWith('$')) {
        val index = Integer.parseInt(arg.substringAfter('$'))
        cells[index] = cells[index] ?: calculate(operations[index], cells, operations)
        cells[index]!!
    } else if (arg == "_") 0
    else Integer.parseInt(arg)
}