/*
 * (C) Sergey A. Tachenov
 * This thing belongs to public domain. Really.
 */
package name.tachenov.leetcode;

import static org.assertj.core.api.Assertions.*;
import org.testng.annotations.*;

public class IsSubsequenceTest {
    
    @Test(dataProvider = "strings")
    public void testSomeMethod(String s, String t, boolean expected) {
        IsSubsequence is = new IsSubsequence();
        assertThat(is.isSubsequence(s, t)).isEqualTo(expected);
    }
    
    @DataProvider
    public Object[][] strings() {
        return new Object[][] {
            {null, null, false},
            {null, "", false},
            {"", null, false},
            {"abc", "ahbgdc", true},
            {"axc", "ahbgdc", false},
            {"abc", "axxxxxbc", true},
            {"abc", "ab", false},
        };
    }
    
}
