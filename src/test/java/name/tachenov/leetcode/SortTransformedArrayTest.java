/*
 * (C) Sergey A. Tachenov
 * This thing belongs to public domain. Really.
 */
package name.tachenov.leetcode;

import static org.assertj.core.api.Assertions.*;
import org.testng.annotations.*;

public class SortTransformedArrayTest {
    
    @Test(dataProvider = "arrays")
    public void sortTransformedArray(int[] nums, int a, int b, int c, int[] expected) {
        SortTransformedArray sta = new SortTransformedArray();
        assertThat(sta.sortTransformedArray(nums, a, b, c)).isEqualTo(expected);
    }
    
    @DataProvider
    public Object[][] arrays() {
        return new Object[][] {
            {new int[0], 0, 0, 0, new int[0]},
            {new int[] {1, 2, 3}, 0, 1, 1, new int[] {2, 3, 4}},
            {new int[] {1, 2, 3}, 0, -1, 0, new int[] {-3, -2, -1}},
            {new int[] {-4, -2, 2, 4}, 1, 3, 5, new int[] {3, 9, 15, 33}},
            {new int[] {-4, -2, 2, 4}, -1, 3, 5, new int[] {-23, -5, 1, 7}},
            {new int[] {-99,-94,-90,-88,-84,-83,-79,-68,-58,-52,-52,-50,-47,-45,-35,-29,-5,-1,9,12,13,25,27,33,36,38,40,46,47,49,57,57,61,63,73,75,79,97,98},
                -2, 44, -56, new int[] {-24014,-21864,-20216,-19416,-17864,-17486,-16014,-14952,-14606,-12296,-9336,-9062,-8006,-7752,-7752,-7502,-7256,-6542,-6086,-5222,-4814,-4046,-4046,-4046,-3014,-2702,-2406,-2264,-1496,-1272,-1064,-782,-326,-326,-206,-102,178,178,184}},
        };
    }
    
}
