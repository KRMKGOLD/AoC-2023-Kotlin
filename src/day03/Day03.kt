package day03

import println
import readInput

fun main() {
    fun part1(input: List<String>): Int {
        var sum = 0

        input.forEachIndexed { i, str ->
            str.forEachIndexed { j, c ->
                if (input[i][j] != '.' && input[i][j].isDigit().not()) {
                    val num1 = input.getOrNull(i - 1)?.filterNumber(j).orEmpty()
                    val num2 = dfs(input.getOrNull(i).orEmpty(), j - 1, true).toIntOrNull()
                    val num3 = dfs(input.getOrNull(i).orEmpty(), j + 1, false).toIntOrNull()
                    val num4 = input.getOrNull(i + 1)?.filterNumber(j).orEmpty()

                    val list = num1 + listOfNotNull(num2, num3) + num4

                    sum += list.sum()
                }
            }
        }
        return sum
    }

    fun part2(input: List<String>): Int {
        var sum = 0

        input.forEachIndexed { i, str ->
            str.forEachIndexed { j, c ->
                if (input[i][j] == '*') {
                    val num1 = input.getOrNull(i - 1)?.filterNumber(j).orEmpty()
                    val num2 = dfs(input.getOrNull(i).orEmpty(), j - 1, true).toIntOrNull()
                    val num3 = dfs(input.getOrNull(i).orEmpty(), j + 1, false).toIntOrNull()
                    val num4 = input.getOrNull(i + 1)?.filterNumber(j).orEmpty()

                    val list = num1 + listOfNotNull(num2, num3) + num4

                    if (list.size > 1) {
                        sum += list.first() * list.last()
                    }
                }
            }
        }

        return sum
    }

    val input = readInput("day03/Day03")
    part1(input).println()
    part2(input).println()
}

fun String.filterNumber(idx: Int): List<Int> {
    val left = dfs(this, idx, true)
    val right = dfs(this, idx, false)

    return if (left.isEmpty() && right.isEmpty()) {
        listOf(
            dfs(this, idx - 1, true),
            dfs(this, idx + 1, false)
        ).mapNotNull { it.toIntOrNull() }
    } else {
        listOf(
            left.dropLast(1) + right
        ).mapNotNull { it.toIntOrNull() }
    }
}

fun dfs(str: String, index: Int, left: Boolean): String {
    val c = str.getOrNull(index)
    if (c == null || c == '.') return ""

    return if (left) {
        dfs(str, index - 1, true) + c
    } else {
        c + dfs(str, index + 1, false)
    }
}