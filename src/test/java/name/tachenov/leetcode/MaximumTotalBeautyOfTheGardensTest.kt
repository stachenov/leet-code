package name.tachenov.leetcode

import org.assertj.core.api.Assertions.*
import org.testng.annotations.*

class MaximumTotalBeautyOfTheGardensTest {

    @Test(dataProvider = "data")
    fun test(flowers: IntArray, newFlowers: Long, target: Int, full: Int, partial: Int, expected: Long) {
        assertThat(MaximumTotalBeautyOfTheGardens().maximumBeauty(flowers, newFlowers, target, full, partial))
            .isEqualTo(expected)
    }

    @DataProvider(name = "data")
    fun data(): Array<Array<Any>> = TestData.load("MaximumTotalBeautyOfTheGardensTest.txt")

}
