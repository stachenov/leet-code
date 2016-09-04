/*
 * (C) Sergey A. Tachenov
 * This thing belongs to public domain. Really.
 */
package name.tachenov.leetcode;

import java.util.*;
import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.data.Percentage.*;
import org.testng.annotations.*;

public class InsertDeleteGetRandomTest {
    
    @Test(dataProvider = "nums")
    public void test(int[] nums) {
        Set<Integer> present = new HashSet<>();
        InsertDeleteGetRandom idgr = new InsertDeleteGetRandom();
        for (int n : nums) {
            if (n > 0) {
                boolean added = idgr.insert(n);
                if (present.contains(n)) {
                    assertThat(added).isFalse();
                } else {
                    assertThat(added).isTrue();
                    present.add(n);
                }
            } else {
                boolean removed = idgr.remove(-n);
                if (present.contains(-n)) {
                    assertThat(removed).isTrue();
                    present.remove(-n);
                } else {
                    assertThat(removed).isFalse();
                }
            }
            Map<Integer, Integer> freq = new HashMap<>();
            int sampleSize = present.size() * 100000;
            for (int i = 0; i < sampleSize; ++i) {
                int random = idgr.getRandom();
                freq.put(random, freq.getOrDefault(random, 0) + 1);
            }
            for (int m : present) {
                assertThat((float) freq.getOrDefault(m, 0) / sampleSize)
                        .isCloseTo(1.0f / present.size(), withPercentage(1));
            }
        }
    }
    
    @DataProvider
    public Object[][] nums() {
        return new Object[][] {
            {new int[] {1}},
            {new int[] {1, 1}},
            {new int[] {1, 2}},
            {new int[] {1, 2, -2}},
            {new int[] {1, 2, -1}},
            {new int[] {+1, -2, +2, -1, -2}},
        };
    }
    
}
