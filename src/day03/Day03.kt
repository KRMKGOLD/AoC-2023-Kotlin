package day03

import println
import readInput

fun main() {
    fun part1(input: List<String>): Int {
        var sum = 0

        input.forEachIndexed { i, str ->
            str.forEachIndexed { j, c ->
                if (input[i][j] != '.' && input[i][j].isDigit().not()) {
                    val str1 = input.getOrNull(i - 1)?.sliceWithPreventException(j - 3..j + 3) ?: ""
                    val str2 = input.getOrNull(i)?.sliceWithPreventException(j - 3..<j) ?: ""
                    val str3 = input.getOrNull(i)?.sliceWithPreventException(j + 1..j + 3) ?: ""
                    val str4 = input.getOrNull(i + 1)?.sliceWithPreventException(j - 3..j + 3) ?: ""

                    val num1 = str1.filterNumber()
                    val num2 = str2.substringAfter(".").toIntOrNull()
                    val num3 = str3.substringBefore(".").toIntOrNull()
                    val num4 = str4.filterNumber()

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
                    val str1 = input.getOrNull(i - 1)?.sliceWithPreventException(j - 3..j + 3) ?: ""
                    val str2 = input.getOrNull(i)?.sliceWithPreventException(j - 3..<j) ?: ""
                    val str3 = input.getOrNull(i)?.sliceWithPreventException(j + 1..j + 3) ?: ""
                    val str4 = input.getOrNull(i + 1)?.sliceWithPreventException(j - 3..j + 3) ?: ""

                    val num1 = str1.filterNumber()
                    val num2 = str2.substringAfter(".").toIntOrNull()
                    val num3 = str3.substringBefore(".").toIntOrNull()
                    val num4 = str4.filterNumber()

                    val list = num1 + listOfNotNull(num2, num3) + num4

                    if (list.size == 2) {
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

fun Char.checkNumber(): Boolean {
    return this in '0'..'9'
}

fun String.sliceWithPreventException(intRange: IntRange): String? {
    return try {
        this.slice(intRange)
    } catch (e: Exception) {
        null
    }
}

fun String.filterNumber(): List<Int> {
    val left = dfs(this, 3, true)
    val right = dfs(this, 3, false)

    return if (left.isEmpty() && right.isEmpty()) {
        listOf(
            dfs(this, 2, true),
            dfs(this, 4, false)
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