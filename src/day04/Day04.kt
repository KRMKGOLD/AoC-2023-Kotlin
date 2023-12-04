package day04

import println
import readInput

fun main() {
    fun part1(input: List<String>): Int {
        var sum = 0

        input.forEach { str ->
            val regex = "\\d+".toRegex()
            val list = str.substringAfter(":").split("|").map {
                regex.findAll(it).toList().map { it.value.toInt() }
            }
            val winnings = list.first()
            val nums = list.last()

            val mergeList = winnings + nums
            val size = mergeList.size - mergeList.distinct().size

            sum += Math.pow(2.0, (size - 1).toDouble()).toInt()
        }

        return sum
    }

    fun part2(input: List<String>): Int {
        val winCountList = MutableList(size = input.size) { 0 }

        input.forEachIndexed { idx, str ->
            val regex = "\\d+".toRegex()
            val gameList = str.substringAfter(":").split("|").map {
                regex.findAll(it).toList().map { it.value.toInt() }
            }
            val winnings = gameList.first()
            val nums = gameList.last()
            val mergeList = winnings + nums
            winCountList[idx] = mergeList.size - mergeList.distinct().size
        }

        val cardCountList = MutableList(size = input.size) { 1 }

        for (idx in input.indices) {
            val winCount = winCountList[idx]

            for (i in 0..<winCount) {
                val sIdx = idx + i + 1

                if (cardCountList.getOrNull(sIdx) != null) {
                    cardCountList[sIdx] += cardCountList[idx]
                }
            }
        }

        return cardCountList.sum()
    }

    val input = readInput("day04/Day04")
    part1(input).println()
    part2(input).println()
}