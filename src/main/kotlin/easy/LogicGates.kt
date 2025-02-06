package easy

import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

//https://www.codingame.com/training/easy/logic-gates

fun main(args : Array<String>) {
    val input = Scanner(System.`in`)
    val n = input.nextInt()
    val m = input.nextInt()
    val inputs = HashMap<String, String>()
    val outputs = ArrayList<Output>()
    for (i in 0 until n) {
        val inputName = input.next()
        val inputSignal = input.next()
        inputs[inputName] = inputSignal
    }
    System.err.println(inputs)
    for (i in 0 until m) {
        val outputName = input.next()
        val type = input.next()
        val inputName1 = input.next()
        val inputName2 = input.next()
        outputs += Output(outputName, type, inputName1, inputName2)
    }
    System.err.println(outputs)
    for (i in 0 until m) {

        val output = outputs[i]
        val signalLength = inputs[output.inputName1]?.length
        val input1 = toBinary(inputs[output.inputName1]!!)
        val input2 = toBinary(inputs[output.inputName2]!!)
        val result = when (output.type) {
            "AND" -> input1 and input2
            "OR" -> input1 or input2
            "XOR" -> input1 xor input2
            "NAND" -> (input1 and input2).inv()
            "NOR" -> (input1 or input2).inv()
            "NXOR" -> (input1 xor input2).inv()
            else -> throw IllegalArgumentException("Operation ${output.type} not supported")
        }
        println("${output.name} ${toSignal(result, signalLength!!)}")
    }
}

data class Output(val name : String, val type : String, val inputName1 : String, val inputName2 : String)

fun toBinary(input : String) : ULong {
    return input.replace("_", "0").replace("-", "1").toULong(2)
}

fun toSignal(output : ULong, length : Int) : String {
    return output.toString(2)
        .padStart(length, '0')
        .takeLast(length)
        .replace("0", "_")
        .replace("1", "-")
}