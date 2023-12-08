package day07

import println
import readInput

fun main() {
    fun List<Pair<String, Int>>.orderCardRating(cardRatingMap: Map<Char, String>): List<Pair<String, Int>> =
        this.sortedByDescending {
            it.first.map { cardRatingMap[it] }
                .joinToString("").toInt()
        }

    fun part1(input: List<String>): Int {
        val cardRatingMap = mapOf(
            'A' to "14",
            'K' to "13",
            'Q' to "12",
            'J' to "11",
            'T' to "10",
            '9' to "09",
            '8' to "08",
            '7' to "07",
            '6' to "06",
            '5' to "05",
            '4' to "04",
            '3' to "03",
            '2' to "02",
            '1' to "01"
        )

        val fiveKindCardList = mutableListOf<Pair<String, Int>>()
        val fourKindCardList = mutableListOf<Pair<String, Int>>()
        val fullHouseKindCardList = mutableListOf<Pair<String, Int>>()
        val tripleKindCardList = mutableListOf<Pair<String, Int>>()
        val twoPairKindCardList = mutableListOf<Pair<String, Int>>()
        val onePairKindCardList = mutableListOf<Pair<String, Int>>()
        val highKindCardList = mutableListOf<Pair<String, Int>>()

        for (str in input) {
            val cardList = str.split(" ")
            val distinctList = cardList[0].toCharArray().distinct()

            when (distinctList.size) {
                1 -> {
                    fiveKindCardList.add(cardList[0] to cardList[1].toInt())
                }

                2 -> {
                    val multiply = cardList[0].groupBy { it }.mapValues { it.value.size }.values
                        .fold(1) { acc, i -> acc * i }

                    if (multiply == 6) { // 3 2, 풀하우스
                        fullHouseKindCardList.add(cardList[0] to cardList[1].toInt())
                    } else if (multiply == 4) { // 4 1, 포카드
                        fourKindCardList.add(cardList[0] to cardList[1].toInt())
                    }
                }

                3 -> {
                    val multiply = cardList[0].groupBy { it }.mapValues { it.value.size }.values
                        .fold(1) { acc, i -> acc * i }

                    if (multiply == 3) { // 트리플, 3 1 1
                        tripleKindCardList.add(cardList[0] to cardList[1].toInt())
                    } else if (multiply == 4) { // 투페어, 2 2 1
                        twoPairKindCardList.add(cardList[0] to cardList[1].toInt())
                    }
                }

                4 -> onePairKindCardList.add(cardList[0] to cardList[1].toInt())
                5 -> highKindCardList.add(cardList[0] to cardList[1].toInt())
            }
        }

        val allCardList = mutableListOf<Pair<String, Int>>()
        listOf(
            fiveKindCardList,
            fourKindCardList,
            fullHouseKindCardList,
            tripleKindCardList,
            twoPairKindCardList,
            onePairKindCardList,
            highKindCardList
        ).forEach {
            allCardList.addAll(it.orderCardRating(cardRatingMap))
        }

        var sum = 0
        allCardList.reversed().forEachIndexed { idx, (_, batting) ->
            sum += (idx + 1).times(batting)
        }

        return sum
    }

    fun part2(input: List<String>): Int {
        val cardRatingMap = mapOf(
            'A' to "14",
            'K' to "13",
            'Q' to "12",
            'T' to "11",
            '9' to "10",
            '8' to "09",
            '7' to "08",
            '6' to "07",
            '5' to "06",
            '4' to "05",
            '3' to "04",
            '2' to "03",
            '1' to "02",
            'J' to "01"
        )

        val fiveKindCardList = mutableListOf<Pair<String, Int>>()
        val fourKindCardList = mutableListOf<Pair<String, Int>>()
        val fullHouseKindCardList = mutableListOf<Pair<String, Int>>()
        val tripleKindCardList = mutableListOf<Pair<String, Int>>()
        val twoPairKindCardList = mutableListOf<Pair<String, Int>>()
        val onePairKindCardList = mutableListOf<Pair<String, Int>>()
        val highKindCardList = mutableListOf<Pair<String, Int>>()

        for (str in input) {
            val cardList = str.split(" ")
            val cards = cardList[0].toCharArray()

            val jokerCount = cards.count { it == 'J' }

            val replaceCard: String = when (jokerCount) {
                4 ->
                    cards.first { it != 'J' }.toString().repeat(5)
                3 -> {
                    val maxC = cards.maxBy { cardRatingMap[it]?.toInt() ?: 0 }
                    cards.map { if (it == 'J') maxC else it }.joinToString("")
                }

                1, 2 -> {
                    val map = cards.filter { it != 'J' }.groupBy { it }.mapValues { it.value.size }
                    val max = map.values.max()
                    when (max) {
                        1 -> {
                            val maxC = cards.maxBy { cardRatingMap[it]?.toInt() ?: 0 }
                            cards.joinToString("").replace('J', maxC)
                        }

                        else -> {
                            val maxC = map.maxBy { it.value }.key
                            cards.joinToString("").replace('J', maxC)
                        }
                    }
                }

                else -> cards.joinToString("")
            }

            val replaceCardArray = replaceCard.toCharArray().distinct()

            when (replaceCardArray.size) {
                1 -> {
                    fiveKindCardList.add(cardList[0] to cardList[1].toInt())
                }

                2 -> {
                    val multiply = replaceCard.groupBy { it }.mapValues { it.value.size }.values
                        .fold(1) { acc, i -> acc * i }

                    if (multiply == 6) { // 3 2, 풀하우스
                        fullHouseKindCardList.add(cardList[0] to cardList[1].toInt())
                    } else if (multiply == 4) { // 4 1, 포카드
                        fourKindCardList.add(cardList[0] to cardList[1].toInt())
                    }
                }

                3 -> {
                    val multiply = replaceCard.groupBy { it }.mapValues { it.value.size }.values
                        .fold(1) { acc, i -> acc * i }

                    if (multiply == 3) { // 트리플, 3 1 1
                        tripleKindCardList.add(cardList[0] to cardList[1].toInt())
                    } else if (multiply == 4) { // 투페어, 2 2 1
                        twoPairKindCardList.add(cardList[0] to cardList[1].toInt())
                    }
                }

                4 -> onePairKindCardList.add(cardList[0] to cardList[1].toInt())
                5 -> highKindCardList.add(cardList[0] to cardList[1].toInt())
            }
        }

        val allCardList = mutableListOf<Pair<String, Int>>()
        listOf(
            fiveKindCardList,
            fourKindCardList,
            fullHouseKindCardList,
            tripleKindCardList,
            twoPairKindCardList,
            onePairKindCardList,
            highKindCardList
        ).forEach {
            allCardList.addAll(it.orderCardRating(cardRatingMap))
        }

        var sum = 0
        allCardList.reversed().forEachIndexed { idx, (_, batting) ->
            sum += (idx + 1).times(batting)
        }

        return sum
    }

//    val input = readInput("day07/Day07")
    val input = readInput("day07/Day07")
    part1(input).println()
    part2(input).println()
}