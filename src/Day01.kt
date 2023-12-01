fun main() {
    fun part1(input: List<String>): Int {
        return input.sumOf {
            val first = it.find { it.isDigit() } ?: 0
            val last = it.findLast { it.isDigit() } ?: 0
            "$first$last".toInt()
        }
    }

    fun part2(input: List<String>): Int {

        var sum = 0

        input.forEach { str ->
            var firstTemp = ""
            var lastTemp = ""

            for (c in str) {
                firstTemp += c

                val num = when {
                    c.isDigit() -> c.digitToInt()
                    firstTemp.contains("one") -> 1
                    firstTemp.contains("two") -> 2
                    firstTemp.contains("three") -> 3
                    firstTemp.contains("four") -> 4
                    firstTemp.contains("five") -> 5
                    firstTemp.contains("six") -> 6
                    firstTemp.contains("seven") -> 7
                    firstTemp.contains("eight") -> 8
                    firstTemp.contains("nine") -> 9
                    else -> 0
                }

                if (num != 0) {
                    sum += num.times(10)
                    firstTemp = ""
                    break
                }
            }

            for (c in str.reversed()) {
                lastTemp = c + lastTemp

                val num = when {
                    c.isDigit() -> c.digitToInt()
                    lastTemp.contains("one") -> 1
                    lastTemp.contains("two") -> 2
                    lastTemp.contains("three") -> 3
                    lastTemp.contains("four") -> 4
                    lastTemp.contains("five") -> 5
                    lastTemp.contains("six") -> 6
                    lastTemp.contains("seven") -> 7
                    lastTemp.contains("eight") -> 8
                    lastTemp.contains("nine") -> 9
                    else -> 0
                }

                if (num != 0) {
                    sum += num
                    lastTemp = ""
                    break
                }
            }
        }

        return sum
    }

    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
