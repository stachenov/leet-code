package name.tachenov.leetcode

import java.util.PriorityQueue

class MaximumProductAfterKIncrements {
    fun maximumProduct(nums: IntArray, k: Int): Int {
        val heap = PriorityQueue(nums.asList())
        for (i in 1..k) {
            val min = heap.remove()
            heap.add(min + 1)
        }
        var result = 1L
        for (n in heap) {
            result = (result * n) % 1_000_000_007L
        }
        return result.toInt()
    }
}
