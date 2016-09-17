/*
 * (C) Sergey A. Tachenov
 * This thing belongs to public domain. Really.
 */
package name.tachenov.leetcode;

import java.util.*;
import static org.assertj.core.api.Assertions.*;
import org.testng.annotations.*;

public class Read4Test {
    
    char[] data;
    
    @Test(dataProvider = "data")
    public void read(String data, List<Integer> reads, List<String> expecteds) {
        this.data = data.toCharArray();
        Read4 read4 = new TestingRead4();
        for (int i = 0; i < reads.size(); ++i) {
            int len = reads.get(i);
            char[] expected = expecteds.get(i).toCharArray();
            char[] buf = new char[len];
            int read = read4.read(buf, len);
            assertThat(read).as("i = " + i).isEqualTo(expected.length);
            assertThat(Arrays.copyOf(buf, read)).as("i = " + i).isEqualTo(expected);
        }
    }
    
    @DataProvider
    public Object[][] data() {
        return new Object[][] {
            {"", Arrays.asList(0), Arrays.asList("")},
            {"", Arrays.asList(1, 1), Arrays.asList("", "")},
            {"a", Arrays.asList(1, 1), Arrays.asList("a", "")},
            {"a", Arrays.asList(2), Arrays.asList("a")},
            {"ab", Arrays.asList(1, 1), Arrays.asList("a", "b")},
            {"abcde", Arrays.asList(1, 4), Arrays.asList("a", "bcde")},
            {"abcde", Arrays.asList(2, 3), Arrays.asList("ab", "cde")},
            {"abcde", Arrays.asList(3, 2), Arrays.asList("abc", "de")},
            {"abcde", Arrays.asList(4, 1), Arrays.asList("abcd", "e")},
            {"abcde", Arrays.asList(5), Arrays.asList("abcde")},
            {"abcdef", Arrays.asList(3, 3), Arrays.asList("abc", "def")},
            {"123456789", Arrays.asList(10, 10), Arrays.asList("123456789", "")},
        };
    }
    
    private class TestingRead4 extends Read4 {
        private int index = 0;

        @Override
        int read4(char[] buf) {
            int read = Math.min(data.length - index, 4);
            System.arraycopy(data, index, buf, 0, read);
            index += read;
            return read;
        }
        
    }
    
}
