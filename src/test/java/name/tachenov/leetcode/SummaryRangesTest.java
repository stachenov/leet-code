package name.tachenov.leetcode;

/*
 * (C) Sergey A. Tachenov
 * This thing belongs to public domain. Really.
 */

import java.util.*;
import static org.assertj.core.api.Assertions.*;
import org.testng.annotations.*;

public class SummaryRangesTest {
    @DataProvider
    public Object[][] data() {
        return new Object[][] {
            {Arrays.asList(1), Arrays.asList(Arrays.asList(new Interval(1, 1)))},
            {Arrays.asList(1, 1), Arrays.asList(Arrays.asList(new Interval(1, 1)),
                    Arrays.asList(new Interval(1, 1)))},
            {Arrays.asList(1, 2), Arrays.asList(Arrays.asList(new Interval(1, 1)),
                    Arrays.asList(new Interval(1, 2)))},
            {Arrays.asList(1, 3), Arrays.asList(Arrays.asList(new Interval(1, 1)),
                    Arrays.asList(new Interval(1, 1), new Interval(3, 3)))},
            {Arrays.asList(2, 1), Arrays.asList(Arrays.asList(new Interval(2, 2)),
                    Arrays.asList(new Interval(1, 2)))},
            {Arrays.asList(2, 1, 1), Arrays.asList(Arrays.asList(new Interval(2, 2)),
                    Arrays.asList(new Interval(1, 2)),
                    Arrays.asList(new Interval(1, 2)))},
            {Arrays.asList(3, 1), Arrays.asList(Arrays.asList(new Interval(3, 3)),
                    Arrays.asList(new Interval(1, 1), new Interval(3, 3)))},
            {Arrays.asList(1, 10, 2), Arrays.asList(Arrays.asList(new Interval(1, 1)),
                    Arrays.asList(new Interval(1, 1), new Interval(10, 10)),
                    Arrays.asList(new Interval(1, 2), new Interval(10, 10)))},
            {Arrays.asList(1, 10, 3), Arrays.asList(Arrays.asList(new Interval(1, 1)),
                    Arrays.asList(new Interval(1, 1), new Interval(10, 10)),
                    Arrays.asList(new Interval(1, 1), new Interval(3, 3), new Interval(10, 10)))},
            {Arrays.asList(1, 10, 9), Arrays.asList(Arrays.asList(new Interval(1, 1)),
                    Arrays.asList(new Interval(1, 1), new Interval(10, 10)),
                    Arrays.asList(new Interval(1, 1), new Interval(9, 10)))},
            {Arrays.asList(1, 3, 2), Arrays.asList(Arrays.asList(new Interval(1, 1)),
                    Arrays.asList(new Interval(1, 1), new Interval(3, 3)),
                    Arrays.asList(new Interval(1, 3)))},
            {Arrays.asList(1, 3, 3), Arrays.asList(Arrays.asList(new Interval(1, 1)),
                    Arrays.asList(new Interval(1, 1), new Interval(3, 3)),
                    Arrays.asList(new Interval(1, 1), new Interval(3, 3)))},
        };
    }

    @Test(dataProvider = "data")
    public void summaryRanges(List<Integer> input, List<List<Interval>> expected) {
        SummaryRanges sr = new SummaryRanges();
        for (int i = 0; i < input.size(); ++i) {
            sr.addNum(input.get(i));
            assertThat(sr.getIntervals()).isEqualTo(expected.get(i));
        }
    }
}

