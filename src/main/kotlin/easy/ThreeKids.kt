package easy

fun main() {
    val limit = 100
    for (x in 1 until limit) {
        if (x % 3 == 0) { // Ensure X is divisible by 3
            for (y in 1 until limit) {
                for (z in 1 until limit) {
                    if (x + y + z <= limit) {
                        // Perform the exchanges sequentially
                        val newX = x - x / 3
                        val newYAfterX = y + x / 3

                        if (newYAfterX % 4 == 0) { // Ensure Y's new total is divisible by 4
                            val newY = newYAfterX - newYAfterX / 4
                            val newZAfterY = z + newYAfterX / 4

                            if (newZAfterY % 5 == 0) { // Ensure Z's new total is divisible by 5
                                val newZ = newZAfterY - newZAfterY / 5
                                val newXAfterZ = newX + newZAfterY / 5

                                // Check if after the exchanges, X, Y, and Z have the same number of coins
                                if (newXAfterZ == newY && newY == newZ) {
                                    println("Initial coins: x = $x, y = $y, z = $z")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
