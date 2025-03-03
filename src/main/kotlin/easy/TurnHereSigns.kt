package easy

import kotlin.math.min

//https://www.codingame.com/training/easy/create-turn-here-signs

fun main(args: Array<String>) {
    val input = "right 3 9 8 6 2"
    val tokens = input.split(" ")
    val direction = tokens[0]
    val howManyArrows = tokens[1].toInt()
    val heightOfArrows = tokens[2].toInt()
    val strokeThickness = tokens[3].toInt()
    val spacingBetweenArrows = tokens[4].toInt()
    val additionalIndent = tokens[5].toInt()

    val spacing = " ".repeat(spacingBetweenArrows)
    val maxIndent = additionalIndent * (heightOfArrows / 2)

    for (i in 0 until heightOfArrows) {
        val baseIndent = additionalIndent * min(i, heightOfArrows - 1 - i)

        val lineIndent = if (direction == "right")
            " ".repeat(baseIndent)
        else if (direction == "left")
            " ".repeat(maxIndent - baseIndent)
        else
            error("Invalid direction: $direction")

        val arrowStroke = when (direction) {
            "right" -> ">".repeat(strokeThickness)
            "left"  -> "<".repeat(strokeThickness)
            else -> error("Invalid direction: $direction")
        }

        val line = lineIndent + (1..howManyArrows).joinToString(spacing) { arrowStroke }
        println(line)
    }
}