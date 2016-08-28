/*
 * (C) Sergey A. Tachenov
 * This thing belongs to public domain. Really.
 */
package name.tachenov.leetcode;

import static org.assertj.core.api.Assertions.assertThat;
import org.testng.annotations.*;

public class LineReflectionTest {
    
    @Test(dataProvider = "points")
    public void lineReflection(int[][] points, boolean expected) {
        LineReflection lr = new LineReflection();
        assertThat(lr.isReflected(points)).isEqualTo(expected);
    }
    
    @DataProvider
    public Object[][] points() {
        return new Object[][] {
            {new int[0][0], true},
            {new int[][] {{0, 0}}, true},
            {new int[][] {{0, 0}, {0, 1}}, true},
            {new int[][] {{-1, 0}, {1, 0}}, true},
            {new int[][] {{0, 0}, {1, 0}}, true},
            {new int[][] {{1, 0}, {2, 0}}, true},
            {new int[][] {{Integer.MIN_VALUE + 1, 0}, {Integer.MAX_VALUE, 0}}, true},
            {new int[][] {{Integer.MIN_VALUE + 1, 0}, {Integer.MAX_VALUE - 1, 0}}, true},
            {new int[][] {{Integer.MIN_VALUE, 0}, {Integer.MAX_VALUE, 0}}, true},
            {new int[][] {{Integer.MAX_VALUE - 1, 0}, {Integer.MAX_VALUE, 0}}, true},
            {new int[][] {{Integer.MIN_VALUE, 0}, {Integer.MIN_VALUE + 1, 0}}, true},
            {new int[][] {{Integer.MIN_VALUE + 10, 0}, {Integer.MIN_VALUE + 11, 0}}, true},
            {new int[][] {{Integer.MIN_VALUE + 10, 0}, {Integer.MIN_VALUE + 20, 0}}, true},
            {new int[][] {{10, 0}, {Integer.MAX_VALUE, 0}}, true},
            {new int[][] {{0, 0}, {1, 0}, {3, 0}}, false},
            {new int[][] {{-1, 0}, {1, 0}, {1, 0}}, true},
        };
    }
    
}
