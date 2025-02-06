package easy

fun main() {
    val limit = 100
    for (x in 1 until limit) {
        for (y in 1 until limit) {
            for (z in 1 until limit) {
                if (x + y + z <= limit && x % 3 == 0 && y % 4 == 0 && z % 5 == 0) {
                    // Perform the exchanges simultaneously
                    val newX = x - x / 3 + z / 5
                    val newY = y - y / 4 + x / 3
                    val newZ = z - z / 5 + y / 4

                    // Check if after the exchanges, X, Y, and Z have the same amount of coins
                    if (newX == newY && newY == newZ) {
                        println("Initial coins: x = $x, y = $y, z = $z")
                    }
                }
            }
        }
    }
}
