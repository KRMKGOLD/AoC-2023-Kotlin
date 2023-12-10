package day09

import println
import readInput
import kotlin.math.abs

fun main() {

    fun part1(input: List<String>): Int {
        var sum = 0

        for (str in input) {
            val list = str.split(" ").map { it.toInt() }
            val allList = mutableListOf(list)

            while (true) {
                val li = allList.last().windowed(2).map { it[1] - it[0] }

                if (li.all { it == 0 }) {
                    allList.add(li)
                    break
                } else {
                    allList.add(li)
                }
            }

            var last = 0

            for (idx in allList.lastIndex downTo 0) {
                last += allList[idx].lastOrNull() ?: 0
            }

            sum += last
        }

        return sum
    }

    fun part2(input: List<String>): Int {
        var sum = 0

        for (str in input) {
            val list = str.split(" ").map { it.toInt() }
            val allList = mutableListOf(list)

            while (true) {
                val li = allList.last().windowed(2).map { it[1] - it[0] }

                if (li.all { it == 0 }) {
                    allList.add(li)
                    break
                } else {
                    allList.add(li)
                }
            }

            var first = allList[allList.lastIndex - 1].firstOrNull()

            for (idx in allList.lastIndex - 2 downTo 0) {
                first = (allList[idx].firstOrNull() ?: 0) - (first ?: 0)
            }

            println(first)
            sum += first ?: 0
        }

        return sum
    }

    val input = readInput("day09/Day09")
    part1(input).println()
    part2(input).println()
}