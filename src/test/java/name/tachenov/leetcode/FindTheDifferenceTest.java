/*
 * (C) Sergey A. Tachenov
 * This thing belongs to public domain. Really.
 */
package name.tachenov.leetcode;

import static org.assertj.core.api.Assertions.assertThat;
import org.testng.annotations.*;

/**
 *
 * @author Sergey A. Tachenov
 */
public class FindTheDifferenceTest {
    
    @Test(dataProvider = "strings")
    public void testSomeMethod(String s, String t, char expected) {
        FindTheDifference ftd = new FindTheDifference();
        assertThat(ftd.findTheDifference(s, t)).isEqualTo(expected);
    }
    
    @DataProvider
    public Object[][] strings() {
        return new Object[][] {
            {"", "a", 'a'},
            {"a", "ab", 'b'},
            {"a", "ba", 'b'},
            {"abc", "ecab", 'e'},
            {"abc", "ceab", 'e'},
            {"abc", "caeb", 'e'},
            {"abc", "cabe", 'e'},
            {"abc", "cbab", 'b'},
        };
    }
    
}
