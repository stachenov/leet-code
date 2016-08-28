/*
 * (C) Sergey A. Tachenov
 * This thing belongs to public domain. Really.
 */
package name.tachenov.leetcode;

import static java.lang.Math.pow;
import java.util.*;
import static org.assertj.core.api.Assertions.assertThat;
import org.testng.annotations.*;

public class CountNumbersWithUniqueDigitsTest {
    
    @Test(dataProvider = "numbers")
    public void countNumbersWithUniqueDigits(int n, int expected) {
        CountNumbersWithUniqueDigits cn = new CountNumbersWithUniqueDigits();
        assertThat(cn.countNumbersWithUniqueDigits(n)).isEqualTo(expected);
    }
    
    @DataProvider
    public Iterator<Object[]> numbers() {
        return new Iterator<Object[]>() {
            private int i = 1;

            @Override
            public boolean hasNext() {
                return i <= 8;
            }

            @Override
            public Object[] next() {
                return new Object[] {i, bruteForce(i++)};
            }
        };
    }

    private static int bruteForce(int n) {
        int count = 0;
        for (int i = 0; i < pow(10, n); ++i) {
            if (allDigitsUnique(i))
                ++count;
        }
        return count;
    }

    private static boolean allDigitsUnique(int i) {
        boolean[] seen = new boolean[10];
        while (i > 0) {
            int d = i % 10;
            if (seen[d])
                return false;
            seen[d] = true;
            i /= 10;
        }
        return true;
    }
    
}
