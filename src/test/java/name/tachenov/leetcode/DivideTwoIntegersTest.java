/*
 * (C) Sergey A. Tachenov
 * This thing belongs to public domain. Really.
 */
package name.tachenov.leetcode;

import static org.assertj.core.api.Assertions.*;
import org.testng.annotations.*;

public class DivideTwoIntegersTest {
    
    @Test(dataProvider = "dividendsAndDivisors")
    public void divide(int dividend, int divisor) {
        DivideTwoIntegers dti = new DivideTwoIntegers();
        int expected;
        if (divisor == 0) {
            expected = Integer.MAX_VALUE;
        } else {
            long quot = (long) dividend / divisor;
            if (quot >= Integer.MIN_VALUE && quot <= Integer.MAX_VALUE)
                expected = (int) quot;
            else
                expected = Integer.MAX_VALUE;
        }
        assertThat(dti.divide(dividend, divisor)).isEqualTo(expected);
    }
    
    @DataProvider
    public Object[][] dividendsAndDivisors() {
        return new Object[][] {
            {1, 1},
            {1, 0},
            {1, 2},
            {2, 1},
            {2, 2},
            {15, 3},
            {15, 4},
            {15, 5},
            {9, 2},
            {9, 3},
            {9, 4},
            {9, 5},
            {9, 6},
            {-1, 1},
            {1, -1},
            {Integer.MIN_VALUE, Integer.MIN_VALUE},
            {Integer.MAX_VALUE, Integer.MIN_VALUE},
            {Integer.MIN_VALUE + 1, Integer.MIN_VALUE},
            {Integer.MIN_VALUE, -1},
            {Integer.MIN_VALUE, 1},
            {Integer.MIN_VALUE, 2},
            {Integer.MIN_VALUE, -2},
            {Integer.MIN_VALUE, 3},
            {Integer.MIN_VALUE, -3},
            {Integer.MIN_VALUE, Integer.MAX_VALUE},
            {Integer.MIN_VALUE, 1 << 30},
            {Integer.MIN_VALUE + 1, -1},
            {1004958205, 2137325331},
            {1004958205, -2137325331},
        };
    }
    
}
