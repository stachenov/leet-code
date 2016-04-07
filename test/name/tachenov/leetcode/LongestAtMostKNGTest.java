/*
 * (C) Sergey A. Tachenov
 * This thing belongs to public domain. Really.
 */
package name.tachenov.leetcode;

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
    public void testLengthOfLongestSubstringKDistinct(String s, int k, int result) {
    }
    
}
