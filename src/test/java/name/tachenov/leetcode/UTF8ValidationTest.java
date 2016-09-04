/*
 * (C) Sergey A. Tachenov
 * This thing belongs to public domain. Really.
 */
package name.tachenov.leetcode;

import static org.assertj.core.api.Assertions.*;
import org.testng.annotations.*;

public class UTF8ValidationTest {
    
    @Test(dataProvider = "data")
    public void testSomeMethod(int[] data, boolean expected) {
        UTF8Validation utf8v = new UTF8Validation();
        assertThat(utf8v.validUtf8(data)).isEqualTo(expected);
    }
    
    @DataProvider
    public Object[][] data() {
        return new Object[][] {
            {new int[] {0x7F}, true},
            {new int[] {0b1100_0000, 0b1000_0000}, true},
            {new int[] {0b1100_0000, 0b0000_0000}, false},
            {new int[] {0b1100_0000, 0b1100_0000}, false},
            {new int[] {0b1100_0000, 0b1011_0000}, true},
            {new int[] {0b1100_0000}, false},
            {new int[] {0xFF}, false},
            {new int[] {0b1110_0000, 0b1011_0000, 0b1011_1000, 0x00}, true},
            {new int[] {0b1110_0000, 0b1011_0000}, false},
            {new int[] {0b1110_0000, 0b1111_0000, 0b1111_0000}, false},
            {new int[] {145}, false},
        };
    }
    
}
