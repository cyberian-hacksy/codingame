package easy

//https://www.codingame.com/training/easy/abcdefghijklmnopqrstuvwxyz

fun main() {
    val n = 10
    val grid = """qadnhwbnyw
iiopcygydk
bahlfiojdc
cfijtdmkgf
dzhkliplzg
efgrmpqryx
loehnovstw
jrsacymeuv
fpnocpdkrs
jlmsvwvuih""".split("\n").map { it.toCharArray() }.toTypedArray()
    val path = Array(n) { BooleanArray(n) { false } }

    val dx = arrayOf(-1, 0, 1, 0)
    val dy = arrayOf(0, 1, 0, -1)

    fun isValid(x: Int, y: Int) = x in 0 until n && y in 0 until n

    fun dfs(x: Int, y: Int, currentChar: Char): Boolean {
        if (!isValid(x, y) || grid[x][y] != currentChar || path[x][y]) return false

        if (currentChar == 'z') {
            path[x][y] = true
            return true
        }

        path[x][y] = true
        val nextChar = currentChar + 1

        for (i in 0..3) {
            val newX = x + dx[i]
            val newY = y + dy[i]
            if (dfs(newX, newY, nextChar)) return true
        }

        path[x][y] = false
        return false
    }

    // Search for 'a' and start DFS from it
    for (i in 0 until n) {
        for (j in 0 until n) {
            if (grid[i][j] == 'a' && !dfs(i, j, 'a')) {
                path[i][j] = false
            }
        }
    }

    // Replace non-path characters with '-'
    for (i in 0 until n) {
        for (j in 0 until n) {
            if (!path[i][j]) grid[i][j] = '-'
        }
    }

    // Print the grid
    for (row in grid) {
        println(row.joinToString(""))
    }
}