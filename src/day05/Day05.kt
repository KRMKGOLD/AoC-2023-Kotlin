package day05

import println
import readInput

val regex = "\\d+".toRegex()

fun main() {
    fun part1(input: List<String>): Int {
        val seedList = regex.findAll(input[0]).map { it.value.toLong() }.toList()
        // val seedList = input[0].substringAfter(":").split(" ").mapNotNull { it.toLongOrNull() }.toList()

        val t = input.joinToString(" ").split(":").drop(2)

        val seedToSoilList = t[0].mappingRangeList()
        val soilToFertilizerList = t[1].mappingRangeList()
        val fertilizerToWaterList = t[2].mappingRangeList()
        val waterToLightList = t[3].mappingRangeList()
        val lightToTemperatureList = t[4].mappingRangeList()
        val temperatureToHumidityList = t[5].mappingRangeList()
        val humidityToLocationList = t[6].mappingRangeList()

        return seedList.asSequence().map { seedToSoilList.transform(it) }
            .map { soilToFertilizerList.transform(it) }
            .map { fertilizerToWaterList.transform(it) }
            .map { waterToLightList.transform(it) }
            .map { lightToTemperatureList.transform(it) }
            .map { temperatureToHumidityList.transform(it) }
            .map { humidityToLocationList.transform(it) }.min().toInt()
    }

    fun part2(input: List<String>): Int {

        return 0
    }

    val input = readInput("day05/Day05")
//    val input = readInput("day05/Day05")

    part1(input).println()
    part2(input).println()
}

fun String.mappingRangeList(): List<Triple<Long, Long, Long>> {
    val str = this.trim()
    return str.split(" ").chunked(3)
        .dropLast(1).map { it.map { it.toLong() } }.map { Triple(it[0], it[1], it[2]) }
}

fun List<Triple<Long, Long, Long>>.transform(before: Long): Long {
    this.forEach {
        if (before in it.second..<it.second + it.third) {
            return it.first + (before - it.second)
        }
    }

    return before
}
