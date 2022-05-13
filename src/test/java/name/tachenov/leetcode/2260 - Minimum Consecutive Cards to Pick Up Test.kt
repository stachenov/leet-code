package name.tachenov.leetcode

import org.assertj.core.api.Assertions.*
import org.junit.jupiter.params.*
import org.junit.jupiter.params.provider.*
import java.util.stream.*

class `2260 - Minimum Consecutive Cards to Pick Up Test` {

    @ParameterizedTest
    @MethodSource("data")
    internal fun test(input: IntArray, expected: Int) {
        assertThat(`2260 - Minimum Consecutive Cards to Pick Up`().minimumCardPickup(input)).isEqualTo(expected)
    }

    companion object {
        @JvmStatic
        fun data(): Stream<Arguments> = Stream.of(
            Arguments.of(intArrayOf(3, 4, 2, 3, 4, 7), 4),
            Arguments.of(intArrayOf(3, 3, 2, 3, 4, 7), 2),
            Arguments.of(intArrayOf(1, 0, 5, 3), -1),
        )
    }

}
