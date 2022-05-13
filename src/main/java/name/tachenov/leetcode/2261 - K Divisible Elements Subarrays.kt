package name.tachenov.leetcode

class `2261 - K Divisible Elements Subarrays` {

    fun countDistinct(nums: IntArray, k: Int, p: Int): Int {
        var result = 0
        for (len in 1..nums.size) {
            val matchingSubarrays = hashSetOf<Subarray>()
            var divisible = 0
            for (i in 0..len - 2) {
                if (nums[i] % p == 0) {
                    ++divisible
                }
            }
            var firstNum = -1
            for (start in 0..nums.size - len) {
                val end = start + len
                val lastNum = nums[end - 1]
                if (lastNum % p == 0) {
                    ++divisible
                }
                if (firstNum != -1 && firstNum % p == 0) {
                    --divisible
                }
                if (divisible <= k) {
                    matchingSubarrays += Subarray(nums, start, end)
                }
                firstNum = nums[start]
            }
            result += matchingSubarrays.size
        }
        return result
    }

    private class Subarray(private val nums: IntArray) {

        constructor(nums: IntArray, start: Int, end: Int) : this(nums.copyOfRange(start, end))

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as Subarray

            if (!nums.contentEquals(other.nums)) return false

            return true
        }

        override fun hashCode(): Int {
            return nums.contentHashCode()
        }

        override fun toString(): String {
            return "Subarray(nums=${nums.contentToString()})"
        }

    }

}
