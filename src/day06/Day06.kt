package day06

import println
import readInput

fun main() {
    val regex = "\\d+".toRegex()

    fun part1(input: List<String>): Int {
        val times = regex.findAll(input[0]).map { it.value.toInt() }.toList()
        val distances = regex.findAll(input[1]).map { it.value.toInt() }.toList()
        val timeToDistances = times.zip(distances)
        val resultList = mutableListOf<Int>()

        timeToDistances.map { (time, distance) ->
            var sum = 0
            (1..<time).forEach {
                if ((time - it) * it > distance) sum++
            }
            resultList.add(sum)
        }

        return resultList.fold(1) { acc, i -> acc * i }
    }

    fun part2(input: List<String>): Int {
        val times = input[0].substringAfter(":").filter { it.isDigit() }.toLong()
        val distances = input[1].substringAfter(":").filter { it.isDigit() }.toLong()

        var sum = 0
        (1..<times).forEach {
            if ((times - it) * it > distances) sum++
        }

        return sum
    }

    val input = readInput("day06/Day06")

    part1(input).println()
    part2(input).println()
}