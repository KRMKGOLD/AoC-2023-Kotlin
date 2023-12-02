package day02

import println
import readInput

fun main() {
    fun part1(input: List<String>): Int {
        var sum = 0

        input.forEachIndexed { idx, str ->
            val list = str.split(*arrayOf(";", ":", ",")).drop(1).map { it.trim().split(" ") }

            for (pair in list) {
                when (pair.last()) {
                    "red" -> if (pair.first().toInt() > 12) return@forEachIndexed
                    "green" -> if (pair.first().toInt() > 13) return@forEachIndexed
                    "blue" -> if (pair.first().toInt() > 14) return@forEachIndexed
                }
            }

            sum += idx + 1
        }

        return sum
    }

    fun part2(input: List<String>): Int {
        var sum = 0

        for (str in input) {
            val list = str.split(*arrayOf(";", ":", ",")).drop(1).map { it.trim().split(" ") }
            var red = 0
            var green = 0
            var blue = 0

            for (map in list) {
                when (map[1]) {
                    "red" -> {
                        red = listOf(red, map[0].toInt()).max()
                    }

                    "green" -> {
                        green = listOf(green, map[0].toInt()).max()
                    }

                    "blue" -> {
                        blue = listOf(blue, map[0].toInt()).max()
                    }
                }
            }
            sum += red * green * blue
        }

        return sum
    }

    val input = readInput("day02/Day02")
    part1(input).println()
    part2(input).println()
}