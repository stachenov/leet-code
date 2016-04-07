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
            {"eceba", 2, 3} // "ece"
        };
    }
    
    @Test(dataProvider = "testCases")
    public void testLengthOfLongestSubstringKDistinct(String s, int k, int expect) {
        LongestAtMostK longk = new LongestAtMostK();
        int result = longk.lengthOfLongestSubstringKDistinct(s, k);
        assertThat(result).isEqualTo(expect);
    }
    
}
