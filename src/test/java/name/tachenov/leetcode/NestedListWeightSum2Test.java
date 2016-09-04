/*
 * (C) Sergey A. Tachenov
 * This thing belongs to public domain. Really.
 */
package name.tachenov.leetcode;

import java.util.*;
import name.tachenov.leetcode.NestedListWeightSum2.NestedInteger;
import static org.assertj.core.api.Assertions.*;
import org.testng.annotations.*;

public class NestedListWeightSum2Test {
    
    @Test(dataProvider = "lists")
    public void depthSumInverse(List<NestedInteger> nestedList, int expected) {
        //NestedListWeightSum2 nlws = new NestedListWeightSum2();
        assertThat(NestedListWeightSum2.depthSumInverse(nestedList)).isEqualTo(expected);
    }
    
    @DataProvider
    public Object[][] lists() {
        return new Object[][] {
            {Arrays.asList(), 0},
            {Arrays.asList(new NestedInteger()), 0},
            {Arrays.asList(new NestedInteger(1)), 1},
            {Arrays.asList(new NestedInteger(Arrays.asList(new NestedInteger(1))),
                    new NestedInteger(Arrays.asList(new NestedInteger(Arrays.asList(new NestedInteger(1)))))
            ), 3},
            {Arrays.asList(new NestedInteger(1),
                    new NestedInteger(Arrays.asList(new NestedInteger(2)))), 4},
            {Arrays.asList(
                    new NestedInteger(Arrays.asList(new NestedInteger(1), new NestedInteger(1))),
                    new NestedInteger(2),
                    new NestedInteger(Arrays.asList(new NestedInteger(1), new NestedInteger(1)))
            ), 8},
            {Arrays.asList(
                    new NestedInteger(1),
                    new NestedInteger(Arrays.asList(new NestedInteger(4),
                            new NestedInteger(Arrays.asList(new NestedInteger(6)))))
            ), 17},
        };
    }
    
}
