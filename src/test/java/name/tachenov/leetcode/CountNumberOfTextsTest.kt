package name.tachenov.leetcode

import org.assertj.core.api.Assertions.*
import org.testng.annotations.*

class CountNumberOfTextsTest {

    @Test(dataProvider = "data")
    fun test(pressedKeys: String, expected: Int) {
        assertThat(CountNumberOfTexts().countTexts(pressedKeys)).isEqualTo(expected)
    }

    @DataProvider(name = "data")
    fun data(): Array<Array<Any>> = arrayOf(
        arrayOf("22233", 8),
        arrayOf("222222222222222222222222222222222222", 82876089),
    )

}
