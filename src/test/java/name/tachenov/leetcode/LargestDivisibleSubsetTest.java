/*
 * (C) Sergey A. Tachenov
 * This thing belongs to public domain. Really.
 */
package name.tachenov.leetcode;

import java.util.*;
import org.assertj.core.api.*;
import org.testng.annotations.*;

public class LargestDivisibleSubsetTest {
    
    @Test(dataProvider = "nums")
    public void largestDivisibleSubset(int[] nums, int expectedSize) {
        LargestDivisibleSubset lds = new LargestDivisibleSubset();
        List<Integer> subset = lds.largestDivisibleSubset(nums);
        assertThat(subset).hasSize(expectedSize);
        assertThat(subset).isDivisible();
    }
    
    @DataProvider
    public Object[][] nums() {
        return new Object[][] {
            {new int[] {}, 0},
            {new int[] {1, 2, 3}, 2},
            {new int[] {1, 2, 4}, 3},
        };
    }

    private SubsetAssert assertThat(List<Integer> subset) {
        return new SubsetAssert(subset);
    }
    
    private static class SubsetAssert extends AbstractListAssert<SubsetAssert, List<Integer>, Integer> {

        public SubsetAssert(List<Integer> actual) {
            super(actual, SubsetAssert.class);
        }

        private void isDivisible() {
            for (int i = 0; i < actual.size(); ++i) {
                for (int j = i + 1; j < actual.size(); ++j) {
                    if (!isDivisible(actual.get(i), actual.get(j)))
                        failWithMessage("(%d, %d) is not divisible", actual.get(i), actual.get(j));
                }
            }
        }

        private static boolean isDivisible(int n1, int n2) {
            return n1 % n2 == 0 || n2 % n1 == 0;
        }
    
    }
    
}
