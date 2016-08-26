/*
 * (C) Sergey A. Tachenov
 * This thing belongs to public domain. Really.
 */
package name.tachenov.leetcode;

import static org.assertj.core.api.Assertions.assertThat;
import org.testng.annotations.*;

public class FirstUniqueCharacterInAStringTest {

    @Test(dataProvider = "strings")
    public void firstUniqueCharacterInAString(String s, int index) {
        FirstUniqueCharacterInAString fuc = new FirstUniqueCharacterInAString();
        assertThat(fuc.firstUniqChar(s)).isEqualTo(index);
    }
    
    @DataProvider
    public Object[][] strings() {
        return new Object[][] {
            {"leetcode", 0},
            {"loveleetcode", 2},
            {"aaaaaaaaaaaa", -1},
        };
    }
    
}
