/*
 * (C) Sergey A. Tachenov
 * This thing belongs to public domain. Really.
 */
package name.tachenov.leetcode;

import static org.assertj.core.api.Assertions.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LongestAtMostKNGTest {
    
    @DataProvider
    public Object[][] testCases() {
        return new Object[][] {
            {"eceba", 2, 3}, // "ece"
            {"aaa", 2, 3},
            {"aba", 2, 3},
            {"abcaba", 2, 3},
            {"abcabas", 2, 3},
            {"abc", 1, 1},
            {"aab", 1, 2},
            {"abcdbef", 3, 4},
            {"\uD87E\uDC1A\uD87E\uDC28", 2, 4}, // SMP
        };
    }
    
    @Test(dataProvider = "testCases")
    public void testLengthOfLongestSubstringKDistinct(String s, int k, int expect) {
        LongestAtMostK longk = new LongestAtMostK();
        int result = longk.lengthOfLongestSubstringKDistinct(s, k);
        assertThat(result).isEqualTo(expect);
    }
    
}
