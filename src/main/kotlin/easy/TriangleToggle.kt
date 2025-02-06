package easy

//https://www.codingame.com/training/easy/triangle-toggle

fun main() {
    val hi = 12
    val wi = 14
    val style = "expanded"
    val howManyTriangles = 1
    val triangles = mutableListOf<List<Int>>()
    triangles.add(listOf(3, 2, 10, 2, 3, 9))

    val effectiveWi = if (style == "expanded") wi * 2 - 1 else wi
    val grid = Array(hi) { y ->
        CharArray(effectiveWi) { x ->
            if (style == "expanded" && x % 2 == 1) ' ' else '*'
        }
    }

    for (triangle in triangles) {
        toggleTriangle(grid, triangle, style)
    }

    printGrid(grid, style)
}

fun isInsideTriangle(px: Int, py: Int, triangle: List<Int>): Boolean {
    val x1 = triangle[0].toDouble()
    val y1 = triangle[1].toDouble()
    val x2 = triangle[2].toDouble()
    val y2 = triangle[3].toDouble()
    val x3 = triangle[4].toDouble()
    val y3 = triangle[5].toDouble()

    val d1 = sign(px.toDouble(), py.toDouble(), x1, y1, x2, y2)
    val d2 = sign(px.toDouble(), py.toDouble(), x2, y2, x3, y3)
    val d3 = sign(px.toDouble(), py.toDouble(), x3, y3, x1, y1)

    val hasNeg = (d1 < 0) || (d2 < 0) || (d3 < 0)
    val hasPos = (d1 > 0) || (d2 > 0) || (d3 > 0)

    return !(hasNeg && hasPos) // True if all positive or all negative (including edges)
}

fun sign(p1x: Double, p1y: Double, p2x: Double, p2y: Double, p3x: Double, p3y: Double): Double {
    return (p1x - p3x) * (p2y - p3y) - (p2x - p3x) * (p1y - p3y)
}

fun toggleTriangle(grid: Array<CharArray>, triangle: List<Int>, style: String) {
    for (y in grid.indices) {
        for (x in grid[y].indices) {
            if (style == "expanded" && x % 2 == 1) continue
            val gridX = if (style == "expanded") x / 2 else x
            if (isInsideTriangle(gridX, y, triangle)) {
                grid[y][x] = if (grid[y][x] == '*') ' ' else '*'
            }
        }
    }
}

fun printGrid(grid: Array<CharArray>, style: String) {
    for (row in grid) {
        println(row.joinToString(""))
    }
}
