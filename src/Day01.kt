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
            val lastIndex = str.lastIndex

            var firstTemp = ""
            var lastTemp = ""

            var isFindFirst = false
            var isFindLast = false

            str.indices.forEach { idx ->
                firstTemp += str[idx]
                lastTemp = str[lastIndex - idx] + lastTemp

                val firstNum = if (str[idx].isDigit()) str[idx].digitToInt()
                else firstTemp.convertStringToNumber()
                val lastNum = if (str[lastIndex - idx].isDigit()) str[lastIndex - idx].digitToInt()
                else lastTemp.convertStringToNumber()

                if (firstNum != 0 && isFindFirst.not()) {
                    sum += firstNum.times(10)
                    isFindFirst = true
                }
                if (lastNum != 0 && isFindLast.not()) {
                    sum += lastNum
                    isFindLast = true
                }
            }
        }
        return sum
    }

    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}

/**
 * 주어진 String에 one, two와 같은 숫자 단어가 들어가 있는 경우, 해당 숫자 단어를 반환하는 확장 함수.
 * Day01 문제에 'zero' 는 해당 사항이 없었으므로 조건문에서 제외.
 */
fun String.convertStringToNumber(): Int = when {
    contains("one") -> 1
    contains("two") -> 2
    contains("three") -> 3
    contains("four") -> 4
    contains("five") -> 5
    contains("six") -> 6
    contains("seven") -> 7
    contains("eight") -> 8
    contains("nine") -> 9
    else -> 0
}