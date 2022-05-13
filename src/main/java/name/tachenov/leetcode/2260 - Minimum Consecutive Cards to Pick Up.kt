package name.tachenov.leetcode

import kotlin.math.*

class `2260 - Minimum Consecutive Cards to Pick Up` {

    fun minimumCardPickup(cards: IntArray): Int {
        val lastSeenAtPosition = hashMapOf<Int, Int>()
        var shortestDistance = Integer.MAX_VALUE
        for (i in cards.indices) {
            val card = cards[i]
            val lastSeenAt = lastSeenAtPosition[card]
            if (lastSeenAt != null) {
                val distance = i - lastSeenAt
                shortestDistance = min(shortestDistance, distance)
                if (shortestDistance == 1)
                    break
            }
            lastSeenAtPosition[card] = i
        }
        return if (shortestDistance == Integer.MAX_VALUE) -1 else shortestDistance + 1
    }

}
