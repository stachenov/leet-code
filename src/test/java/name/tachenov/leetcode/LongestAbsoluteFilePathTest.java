/*
 * (C) Sergey A. Tachenov
 * This thing belongs to public domain. Really.
 */
package name.tachenov.leetcode;

import static org.assertj.core.api.Assertions.*;
import org.testng.annotations.*;

public class LongestAbsoluteFilePathTest {

    @Test(dataProvider = "fileSystems")
    public void longestAbsoluteFilePath(String fileSystem, int expectedLength) {
        LongestAbsoluteFilePath lafp = new LongestAbsoluteFilePath();
        assertThat(lafp.lengthLongestPath(fileSystem)).isEqualTo(expectedLength);
    }
    
    @DataProvider
    public Object[][] fileSystems() {
        return new Object[][] {
            {"", 0},
            {"dir", 0},
            {"a.b", 3},
            {"dir\n\ta.b", 7},
            {"dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext", 32},
        };
    }
}
