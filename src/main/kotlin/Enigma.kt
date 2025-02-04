fun caesarShift(message: String, startShift: Int, isEncoding: Boolean): String {
    return message.mapIndexed { index, c ->
        val shift = if (isEncoding) startShift + index else -startShift - index
        val newCharIndex = (c.code - 'A'.code + shift) % 26
        val wrappedCharIndex = if (newCharIndex < 0) newCharIndex + 26 else newCharIndex
        ('A'.code + wrappedCharIndex).toChar()
    }.joinToString("")
}

fun applyRotor(message: String, rotor: String, isEncoding: Boolean): String {
    val alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
    return message.map { c ->
        if (isEncoding) rotor[alphabet.indexOf(c)] else alphabet[rotor.indexOf(c)]
    }.joinToString("")
}

fun encodeDecode(
    operation: String,
    startShift: Int,
    rotorI: String,
    rotorII: String,
    rotorIII: String,
    message: String
): String {
    return if (operation == "ENCODE") {
        val shiftedMessage = caesarShift(message, startShift, true)
        val rotorOneOutput = applyRotor(shiftedMessage, rotorI, true)
        val rotorTwoOutput = applyRotor(rotorOneOutput, rotorII, true)
        applyRotor(rotorTwoOutput, rotorIII, true)
    } else {
        val rotorThreeOutput = applyRotor(message, rotorIII, false)
        val rotorTwoOutput = applyRotor(rotorThreeOutput, rotorII, false)
        val rotorOneOutput = applyRotor(rotorTwoOutput, rotorI, false)
        caesarShift(rotorOneOutput, startShift, false)
    }
}

fun main() {
    val operation = "ENCODE"
    val startShift = 4
    val rotorI = "BDFHJLCPRTXVZNYEIWGAKMUSQO"
    val rotorII = "AJDKSIRUXBLHWTMCQGZNPYFVOE"
    val rotorIII = "EKMFLGDQVZNTOWYHXUSPAIBRCJ"
    val message = "AAA"

    val result = encodeDecode(operation, startShift, rotorI, rotorII, rotorIII, message)
    println(result)
}