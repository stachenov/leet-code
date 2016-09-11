/*
 * (C) Sergey A. Tachenov
 * This thing belongs to public domain. Really.
 */
package name.tachenov.leetcode;

import static org.assertj.core.api.Assertions.assertThat;
import static org.testng.Assert.*;
import org.testng.annotations.*;

/**
 *
 * @author Sergey A. Tachenov
 */
public class IntegerReplacementTest {
    
    @Test(dataProvider = "nums")
    public void integerReplacement(int n, int expected) {
        assertThat(new IntegerReplacement().integerReplacement(n)).isEqualTo(expected);
    }
    
    @DataProvider
    public Object[][] nums() {
        return new Object[][] {
            {1, 0},
            {2, 1},
            {3, 2},
            {8, 3},
            {7, 4},
            {65535, 17},
            {100000000, 31},
            {2147483647, 32},
        };
    }
    
}
