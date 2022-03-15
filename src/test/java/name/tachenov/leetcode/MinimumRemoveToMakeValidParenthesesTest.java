package name.tachenov.leetcode;

import org.testng.annotations.*;

import static org.assertj.core.api.Assertions.*;

public class MinimumRemoveToMakeValidParenthesesTest {
    @DataProvider
    public static Object[][] data() {
        return new Object[][] {
                { "lee(t(c)o)de)", "lee(t(c)o)de" },
                { "a)b(c)d", "ab(c)d" },
                { "))((", "" }
        };
    }

    @Test(dataProvider = "data")
    public void test(String input, String expected) {
        final var actual = new MinimumRemoveToMakeValidParentheses().minRemoveToMakeValid(input);
        assertThat(actual).hasSameSizeAs(expected);
        assertThat(isValid(actual)).isTrue();
    }

    private boolean isValid(String s) {
        var sum = 0;
        for (int i = 0; i < s.length(); i++) {
            final var c = s.charAt(i);
            if (c == '(') {
                ++sum;
            } else if (c == ')') {
                --sum;
            }
            if (sum < 0) {
                return false;
            }
        }
        return sum == 0;
    }
}
