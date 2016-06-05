/*
 * (C) Sergey A. Tachenov
 * This thing belongs to public domain. Really.
 */
package name.tachenov.leetcode;

import static org.assertj.core.api.Assertions.assertThat;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Tests for Android Unlock Patterns #351.
 * @author Sergey A. Tachenov
 */
public class AndroidUnlockPatternsTest {
    private static final String NUMBER_OF_PATTERNS_DATA_PROVIDER = "numberOfPatterns";
    
    @DataProvider(name = NUMBER_OF_PATTERNS_DATA_PROVIDER)
    public Object[][] numberOfPatternsData() {
        return new Object[][] {
            {1, 1, 9},
            {2, 2, 56},
        };
    }
    
    @Test(dataProvider = NUMBER_OF_PATTERNS_DATA_PROVIDER)
    public void numberOfPatterns(int m, int n, int expected) {
        AndroidUnlockPatterns aup = new AndroidUnlockPatterns();
        int np = aup.numberOfPatterns(m, n);
        assertThat(np).isEqualTo(expected);
    }
    
}
