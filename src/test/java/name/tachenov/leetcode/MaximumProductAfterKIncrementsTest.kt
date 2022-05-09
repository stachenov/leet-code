package name.tachenov.leetcode

import org.assertj.core.api.Assertions.*
import org.testng.annotations.*

class MaximumProductAfterKIncrementsTest {

    @Test(dataProvider = "data")
    fun test(nums: IntArray, k: Int, expected: Int) {
        assertThat(MaximumProductAfterKIncrements().maximumProduct(nums, k)).isEqualTo(expected)
    }

    @DataProvider(name = "data")
    fun data(): Array<Array<Any>> = arrayOf(
        arrayOf(intArrayOf(24,5,64,53,26,38), 54, 180820950),
    )

}
