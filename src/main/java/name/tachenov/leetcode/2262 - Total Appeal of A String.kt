package name.tachenov.leetcode

class `2262 - Total Appeal of A String` {

    fun appealSum(s: String): Long {
        val lastPositions = CharToPositionMap()
        var result = 0L
        var totalAppealOfSuffixes = 0L
        for (i in s.indices) {
            val c = s[i]
            val lastPosition = lastPositions[c]
            val suffixesWithIncreasedAppeal = i - lastPosition
            val totalAppealOfLongerSuffixes = totalAppealOfSuffixes + suffixesWithIncreasedAppeal
            result += totalAppealOfLongerSuffixes
            totalAppealOfSuffixes = totalAppealOfLongerSuffixes
            lastPositions[c] = i
        }
        return result
    }

    private class CharToPositionMap {

        private val positions = IntArray(26) { -1 }

        operator fun get(c: Char): Int = positions[c - 'a']

        operator fun set(c: Char, position: Int) {
            positions[c - 'a'] = position
        }

    }

}
