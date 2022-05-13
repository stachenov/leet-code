package name.tachenov.leetcode

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.*
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.*
import java.util.stream.*

class `2261 - K Divisible Elements Subarrays Test` {

    @ParameterizedTest
    @MethodSource("data")
    internal fun test(nums: IntArray, k: Int, p: Int, expected: Int) {
        Assertions.assertThat(`2261 - K Divisible Elements Subarrays`().countDistinct(nums, k, p)).isEqualTo(expected)
    }

    companion object {
        @JvmStatic
        fun data(): Stream<Arguments> = Stream.of(
            Arguments.of(intArrayOf(2, 3, 3, 2, 2), 2, 2, 11),
            Arguments.of(intArrayOf(16, 17, 4, 12, 3), 4, 1, 14),
        )

    }

}
