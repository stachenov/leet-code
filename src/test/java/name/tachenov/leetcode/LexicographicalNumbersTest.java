/*
 * (C) Sergey A. Tachenov
 * This thing belongs to public domain. Really.
 */
package name.tachenov.leetcode;

import java.util.*;
import java.util.stream.*;
import static org.assertj.core.api.Assertions.*;
import org.testng.annotations.*;

public class LexicographicalNumbersTest {
    
    @Test(dataProvider = "n")
    public void lexicalOrder(int n, List<Integer> expected) {
        LexicographicalNumbers ln = new LexicographicalNumbers();
        assertThat(ln.lexicalOrder(n)).isEqualTo(expected);
    }
    
    @DataProvider
    public Iterator<Object[]> n() {
        List<Object[]> tests = new ArrayList<>();
        addTest(tests, 0);
        addTest(tests, 1);
        addTest(tests, 9);
        addTest(tests, 10);
        addTest(tests, 99);
        addTest(tests, 100);
        addTest(tests, 101);
        return tests.iterator();
    }

    private void addTest(List<Object[]> tests, int n) {
        String[] strings = new String[n];
        for (int i = 1; i <= n; ++i)
            strings[i - 1] = String.valueOf(i);
        Arrays.sort(strings);
        List<Integer> numbers = Stream.of(strings)
                .mapToInt(Integer::parseInt)
                .boxed()
                .collect(Collectors.toList());
        tests.add(new Object[] {n, numbers});
    }
}
