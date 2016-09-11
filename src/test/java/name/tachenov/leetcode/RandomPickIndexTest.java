/*
 * (C) Sergey A. Tachenov
 * This thing belongs to public domain. Really.
 */
package name.tachenov.leetcode;

import static org.assertj.core.api.Assertions.assertThat;
import static org.testng.Assert.*;
import org.testng.annotations.*;

public class RandomPickIndexTest {
    
    @Test(dataProvider = "arrays")
    public void pick(int[] nums, int n) {
        RandomPickIndex rpi = new RandomPickIndex(nums);
        assertThat(nums[rpi.pick(n)]).isEqualTo(n);
    }
    
    @DataProvider
    public Object[][] arrays() {
        return new Object[][] {
            {new int[] {0, 1, 1}, 0},
            {new int[] {0, 1, 1}, 1},
        };
    }
    
}
