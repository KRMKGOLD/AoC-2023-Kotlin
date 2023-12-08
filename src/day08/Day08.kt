package day08

import println
import readInput

data class Node(
    val value: String,
    val left: String,
    val right: String
)

fun gcd(a: Long, b: Long): Long {
    if (a == 0L) return b
    return gcd(b % a, a)
}

fun lcm(a: Long, b: Long): Long {
    return a * b / gcd(a, b)
}

fun main() {
    fun part1(input: List<String>): Int {
        val directions = input[0]
        val englishRegex = Regex("[a-zA-Z]+")

        val nodes = input.drop(2).map { str ->
            val (from, left, right) = englishRegex.findAll(str).toList().map { it.value }

            Node(from, left, right)
        }
        var currentNodes = nodes.first { it.value == "AAA" }

        var count = 1

        while (true) {
            directions.forEach {
                if (it == 'L') {
                    currentNodes = nodes.find { it.value == currentNodes.left }!!
                } else if (it == 'R') {
                    currentNodes = nodes.find { it.value == currentNodes.right }!!
                }

                if (currentNodes.value == "ZZZ") {
                    return count
                } else {
                    count++
                }
            }
        }
    }

    fun part2(input: List<String>): Long {
        val directions = input[0]
        val englishRegex = Regex("[a-zA-Z]+")

        val nodes = input.drop(2).map { str ->
            val (from, left, right) = englishRegex.findAll(str).toList().map { it.value }

            Node(from, left, right)
        }
        val currentNodes = nodes.filter { it.value.last() == 'A' }.toMutableList()

        val findZNodesCount = mutableListOf<Long>()

        currentNodes.forEach { node ->
            var currentNode = node
            var count = 1
            var breakLoop = false

            while (!breakLoop) {
                directions.forEach { direction ->
                    if (direction == 'L') {
                        currentNode = nodes.find { it.value == currentNode.left }!!
                    } else if (direction == 'R') {
                        currentNode = nodes.find { it.value == currentNode.right }!!
                    }

                    if (currentNode.value.last() == 'Z') {
                        findZNodesCount.add(count.toLong())
                        breakLoop = true
                    } else {
                        count++
                    }
                }
            }
        }

        return findZNodesCount.fold(1) { acc, l -> lcm(acc, l) }
    }

    val input = readInput("day08/Day08")
    part1(input).println()
    part2(input).println()
}