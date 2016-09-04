/*
 * (C) Sergey A. Tachenov
 * This thing belongs to public domain. Really.
 */
package name.tachenov.leetcode;

import static org.assertj.core.api.Assertions.*;
import org.testng.annotations.*;

public class DecodeStringTest {
    
    @Test(dataProvider = "strings")
    public void decodeString(String s, String expected) {
        DecodeString ds = new DecodeString();
        assertThat(ds.decodeString(s)).isEqualTo(expected);
    }
    
    @DataProvider
    public Object[][] strings() {
        return new Object[][] {
            {null, null},
            {"3[a]2[bc]", "aaabcbc"},
            {"3[a2[c]]", "accaccacc"},
            {"2[abc]3[cd]ef", "abcabccdcdcdef"},
            {"10[abc]", "abcabcabcabcabcabcabcabcabcabc"},
            {"1[a2[b]]bc3[a]", "abbbcaaa"},
        };
    }
    
}
