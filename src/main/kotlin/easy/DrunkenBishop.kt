package easy

//https://www.codingame.com/training/easy/ascii-art-the-drunken-bishop-algorithm

fun main(args: Array<String>) {
    val fingerprint = "fc:94:b0:c1:e5:b0:98:7c:58:43:99:76:97:ee:9f:b7"
    print(drunkenBishop(fingerprint))
}

fun drunkenBishop(fingerprint: String): String {
    val symbols = " .o+=*BOX@%&#/^".toCharArray()
    val board = Array(9) { IntArray(17) }
    var x = 8
    var y = 4
    board[y][x]++

    val moves = mapOf(
        "00" to Pair(-1, -1),
        "01" to Pair(-1, 1),
        "10" to Pair(1, -1),
        "11" to Pair(1, 1)
    )

    val binaryString = fingerprint.split(":")
        .map { it.toInt(16).toString(2).padStart(8, '0') }
        .map { it.chunked(2).reversed() }
        .flatten()

    binaryString.forEach { bits ->
        val (dy, dx) = moves[bits]!!
        x = (x + dx).coerceIn(0, 16)
        y = (y + dy).coerceIn(0, 8)
        board[y][x]++
    }

    return buildString {
        append("+---[CODINGAME]---+\n")
        for ((rowIndex, row) in board.withIndex()) {
            append("|")
            row.forEachIndexed { index, count ->
                append(
                    when {
                        rowIndex == 4 && index == 8 -> 'S'
                        rowIndex == y && index == x -> 'E'
                        else -> symbols[count % symbols.size]
                    }
                )
            }
            append("|\n")
        }
        append("+-----------------+")
    }
}