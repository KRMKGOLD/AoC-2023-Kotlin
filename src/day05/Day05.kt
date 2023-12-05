package day05

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.withContext
import println
import readInput

val regex = "\\d+".toRegex()

suspend fun main() {

    val input = readInput("day05/Day05")

    val t = input.joinToString(" ").split(":").drop(2)

    val seedToSoilList = t[0].mappingRangeList()
    val soilToFertilizerList = t[1].mappingRangeList()
    val fertilizerToWaterList = t[2].mappingRangeList()
    val waterToLightList = t[3].mappingRangeList()
    val lightToTemperatureList = t[4].mappingRangeList()
    val temperatureToHumidityList = t[5].mappingRangeList()
    val humidityToLocationList = t[6].mappingRangeList()

    fun part1(): Int {
        val seedList = input[0].substringAfter(":").split(" ").mapNotNull { it.toLongOrNull() }.toList()

        return seedList.asSequence().map { seedToSoilList.transform(it) }
            .map { soilToFertilizerList.transform(it) }
            .map { fertilizerToWaterList.transform(it) }
            .map { waterToLightList.transform(it) }
            .map { lightToTemperatureList.transform(it) }
            .map { temperatureToHumidityList.transform(it) }
            .map { humidityToLocationList.transform(it) }.min().toInt()
    }

    suspend fun part2() {
        val seedList = input[0].substringAfter(":").split(" ")
            .mapNotNull { it.toLongOrNull() }.chunked(2).map { it[0]..<(it[0] + it[1]) }

        withContext(Dispatchers.IO) {
            seedList.map { range ->
                async {
                    range.minOf { seed ->
                        listOf(
                            seedToSoilList,
                            soilToFertilizerList,
                            fertilizerToWaterList,
                            waterToLightList,
                            lightToTemperatureList,
                            temperatureToHumidityList,
                            humidityToLocationList
                        ).fold(seed) { acc, list ->
                            list.transform(acc)
                        }
                    }
                }
            }.awaitAll().min().println()
        }
    }

//    part1().println()
    part2().println()
}

fun String.mappingRangeList(): List<Triple<Long, Long, Long>> {
    val str = this.trim()
    return str.split(" ").chunked(3)
        .dropLast(1).map { it.map { it.toLong() } }.map { Triple(it[0], it[1], it[2]) }
}

fun List<Triple<Long, Long, Long>>.transform(before: Long): Long {
    this.forEach {
        if (it.second <= before && before <= it.second + it.third - 1) {
            return it.first + (before - it.second)
        }
    }

    return before
}

//fun List<Triple<Long, Long, Long>>.reverseTransform(before: Long): Long {
//    val list = this.find { before >= it.first && before <= it.first + it.third - 1 } ?: return before
//
//    return list.second + (before - list.first)
//}