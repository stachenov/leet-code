/*
 * (C) Sergey A. Tachenov
 * This thing belongs to public domain. Really.
 */
package name.tachenov.leetcode;

import java.io.*;
import static org.assertj.core.api.Assertions.*;
import org.testng.annotations.*;
import org.testng.reporters.*;

/**
 *
 * @author Sergey A. Tachenov
 */
public class PerfectRectangleTest {

    @Test(dataProvider = "rectangles")
    public void testSomeMethod(int[][] rectangles, boolean expected) {
        PerfectRectangle pr = new PerfectRectangle();
        assertThat(pr.isRectangleCover(rectangles)).isEqualTo(expected);
    }

    @DataProvider
    public Object[][] rectangles() throws IOException {
        return new Object[][]{
            {new int[][]{
                {1, 1, 3, 3},
                {3, 1, 4, 2},
                {3, 2, 4, 4},
                {1, 3, 2, 4},
                {2, 3, 3, 4},
            }, true},
            {new int[][]{
                {1, 1, 2, 3},
                {1, 3, 2, 4},
                {3, 1, 4, 2},
                {3, 2, 4, 4},
            }, false},
            {new int[][]{
                {1, 1, 3, 3},
                {3, 1, 4, 2},
                {1, 3, 2, 4},
                {3, 2, 4, 4},
            }, false},
            {new int[][]{
                {1, 1, 3, 3},
                {3, 1, 4, 2},
                {1, 3, 2, 4},
                {2, 2, 4, 4},
            }, false},
            {LeetCode.array2d(Files.readFile(new File("testData/PerfectRectangleTest5.txt"))), true},
        };
    }

}
