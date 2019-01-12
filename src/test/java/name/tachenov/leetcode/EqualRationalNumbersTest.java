package name.tachenov.leetcode;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class EqualRationalNumbersTest {
    @DataProvider
    public static Object[][] testData() {
        return new Object[][] {
                {"0", "0", true},
                {"0", "00", true},
                {"0", "1", false},
                {"0", "01", false},
                {"1", "01", true},
                {"1.1", "1.1", true},
                {"1.1", "1.2", false},
                {"1", "1.", true},
                {"1.1(1)", "1.(1)", true},
                {"1.(3)", "1.(33)", true},
        };
    }

    @Test(dataProvider = "testData")
    public void test(String s, String t, boolean expected) {
        if (expected)
            assertThat(RationalNumber.parse(s)).isEqualTo(RationalNumber.parse(t));
        else
            assertThat(RationalNumber.parse(s)).isNotEqualTo(RationalNumber.parse(t));
    }
}
