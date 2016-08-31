/*
 * (C) Sergey A. Tachenov
 * This thing belongs to public domain. Really.
 */
package name.tachenov.leetcode;

import java.util.stream.*;
import static org.assertj.core.api.Assertions.assertThat;
import org.testng.annotations.*;

public class RearrangeStringKDistanceApartTest {
    
    @Test(dataProvider = "strings")
    public void rearrangeString(String str, int k, String expected) {
        RearrangeStringKDistanceApart rka = new RearrangeStringKDistanceApart();
        assertThat(rka.rearrangeString(str, k)
            .codePoints().boxed().collect(Collectors.toSet()))
                .isEqualTo(expected.codePoints().boxed().collect(Collectors.toSet()));
    }
    
    @DataProvider
    public Object[][] strings() {
        return new Object[][] {
            {"", 1, ""},
            {"a", 0, "a"},
            {"aa", 0, "aa"},
            {"a", 1, "a"},
            {"aa", 1, "aa"},
            {"ab", 1, "ab"},
            {"aab", 2, "aba"},
            {"aaabc", 2, "abaca"},
            {"aab", 3, ""},
            {"aabbcc", 3, "acbacb"},
        };
    }
    
}
