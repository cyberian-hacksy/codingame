package easy

import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

//https://www.codingame.com/training/easy/longest-coast

fun main(args : Array<String>) {
    val input = Scanner(System.`in`)
    val n = input.nextInt()
    System.err.println(n)
    if (input.hasNextLine()) {
        input.nextLine()
    }

    val map = HashMap<Pair<Int, Int>, Char>()
    for (i in 0 until n) {
        val row = input.nextLine()
        System.err.println(row)
        for (j in 0 until n) {
            map[Pair(i, j)] = row[j]
        }
    }

    val islands = ArrayList<Int>()

    do {
        val nextIsland = map.entries
            .sortedWith(compareBy({ it.key.first }, { it.key.second }))
            .filter { it.value == '#' }
            .map { it.key }
            .first()
        islands.add(bfs(nextIsland, map))
    } while (map.values.contains('#'))

    val longestCoast = islands.max()
    val island = islands.indexOfFirst { it == longestCoast} + 1
    println("$island $longestCoast")
}

fun bfs(source: Pair<Int, Int>, map: HashMap<Pair<Int, Int>, Char>): Int {
    val queue = LinkedList<Pair<Int, Int>>()
    val enqueued = mutableSetOf<Pair<Int, Int>>()
    val visited = ArrayList<Pair<Int, Int>>()
    var water = 0

    queue.add(source)
    enqueued.add(source)
    while (true) {
        val vertex = queue.poll() ?: break
        visited.add(vertex)
        if (map[vertex] == '~') water++
        else if (map[vertex] == '#') {
            map[vertex] = '0'
            val neighborEdges = edges(vertex, map)
            neighborEdges.forEach {
                if (!enqueued.contains(it)) {
                    queue.add(it)
                    enqueued.add(it)
                }
            }
        }
    }

    return water
}

fun edges(vertex: Pair<Int, Int>, map: HashMap<Pair<Int, Int>, Char>): List<Pair<Int, Int>> {
    return setOf(
        Pair(vertex.first, vertex.second - 1),
        Pair(vertex.first, vertex.second + 1),
        Pair(vertex.first - 1, vertex.second),
        Pair(vertex.first + 1, vertex.second),
    )
        .filter { map.keys.contains(it) }
}