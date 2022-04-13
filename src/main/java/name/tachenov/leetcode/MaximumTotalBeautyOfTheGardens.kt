package name.tachenov.leetcode

import kotlin.math.*

class MaximumTotalBeautyOfTheGardens {

    fun maximumBeauty(flowers: IntArray, newFlowers: Long, target: Int, full: Int, partial: Int): Long {
        flowers.sort()
        val completeResults = computeCompleteResults(flowers, newFlowers, target)
        val incompleteResults = computeIncompleteResults(flowers, target)
        var max = 0L
        for (complete in completeResults) {
            var remaining = newFlowers - complete.cost
            val incomplete = findBestAffordableMinimum(incompleteResults, remaining, flowers.size - complete.count)
            remaining -= incomplete.cost
            var minimum = incomplete.minimum.toLong()
            if (incomplete.count > 0) // division by zero check
                minimum += (remaining / incomplete.count)
            minimum = minimum.coerceAtMost((target - 1).toLong())
            val beauty = complete.count.toLong() * full + minimum * partial
            max = max(max, beauty)
        }
        return max
    }

    private fun findBestAffordableMinimum(
        incompleteResults: List<IncompleteGardensResult>,
        available: Long,
        maxCount: Int,
    ): IncompleteGardensResult {
        val index = incompleteResults.binarySearch(fromIndex = 0, toIndex = maxCount + 1) {
            it.cost.compareTo(available)
        }
        return if (index >= 0)
            incompleteResults[index]
        else
            incompleteResults[-index - 2] // IndexOutOfBounds impossible, as available >= 0 and the first element’s cost is 0
    }

    private fun computeCompleteResults(flowers: IntArray, newFlowers: Long, target: Int): List<CompleteGardensResult> {
        val result = mutableListOf(CompleteGardensResult(count = 0, cost = 0L))
        var cost = 0L
        var complete = 0
        for (n in flowers.reversed()) {
            val remaining = newFlowers - cost
            val need = (target - n).coerceAtLeast(0)
            if (remaining < need)
                break
            cost += need
            ++complete
            result += CompleteGardensResult(complete, cost)
        }
        return result
    }

    private fun computeIncompleteResults(flowers: IntArray, target: Int): List<IncompleteGardensResult> {
        val result = mutableListOf(IncompleteGardensResult(count = 0, cost = 0L, minimum = 0))
        for (n in flowers) {
            val prevCount = result.lastIndex
            val prevCost = result.last().cost
            val prevMin = result.last().minimum
            val count = prevCount + 1
            result += if (n >= target) {
                IncompleteGardensResult(count, cost = Long.MAX_VALUE, minimum = 0)
            } else {
                val raise = n - prevMin
                IncompleteGardensResult(count, cost = prevCost + prevCount * raise, minimum = n)
            }
        }
        return result
    }
}

private data class CompleteGardensResult(val count: Int, val cost: Long)
private data class IncompleteGardensResult(val count: Int, val cost: Long, val minimum: Int)
