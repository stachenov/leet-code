package name.tachenov.leetcode

class CountNumberOfTexts {

    fun countTexts(pressedKeys: String): Int {
        var count = 1
        var i = 0
        val memo = Memo(pressedKeys.length)
        while (i < pressedKeys.length) {
            val key = pressedKeys[i]
            var repeats = 0
            while (i < pressedKeys.length && pressedKeys[i] == key) {
                ++repeats
                ++i
            }
            count = count mul1097 countCombinations(memo, repeats, MAX_REPETITIONS.getValue(key))
        }
        return count
    }

    private fun countCombinations(memo: Memo, repeats: Int, maxRepeats: Int): Int {
        memo.get(repeats, maxRepeats)?.let { return it }
        val maxRepeatsAtStart = repeats.coerceAtMost(maxRepeats)
        var count = 0
        for (repeatsAtStart in 1..maxRepeatsAtStart) {
            count = count add1097 countCombinations(memo, repeats - repeatsAtStart, maxRepeats)
        }
        memo.put(repeats, maxRepeats, count)
        return count
    }

    private class Memo(size: Int) {

        private val memo = Array(size + 1) { IntArray(MAX_REPETITIONS.values.maxOf { it } + 1) }

        init {
            memo[0][3] = 1
            memo[0][4] = 1
        }

        fun get(repeats: Int, maxRepeats: Int): Int? = memo[repeats][maxRepeats].let {
            if (it == 0)
                null
            else
                it
        }

        fun put(repeats: Int, maxRepeats: Int, result: Int) {
            memo[repeats][maxRepeats] = result
        }

    }

    companion object {
        private val MAX_REPETITIONS = mapOf(
            '2' to 3, '3' to 3,
            '4' to 3, '5' to 3, '6' to 3,
            '7' to 4, '8' to 3, '9' to 4,
        )
    }

}

private infix fun Int.add1097(other: Int): Int = ((toLong() + other) % 1_000_000_007L).toInt()

private infix fun Int.mul1097(other: Int): Int = ((toLong() * other) % 1_000_000_007L).toInt()
