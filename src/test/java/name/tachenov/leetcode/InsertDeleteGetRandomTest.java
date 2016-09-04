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
        Map<Integer, Integer> present = new HashMap<>();
        int total = 0;
        InsertDeleteGetRandom idgr = new InsertDeleteGetRandom();
        for (int n : nums) {
            if (n > 0) {
                boolean added = idgr.insert(n);
                if (present.getOrDefault(n, 0) > 0) {
                    assertThat(added).isFalse();
                } else {
                    assertThat(added).isTrue();
                }
                present.put(n, present.getOrDefault(n, 0) + 1);
                ++total;
            } else {
                boolean removed = idgr.remove(-n);
                if (present.getOrDefault(-n, 0) > 0) {
                    assertThat(removed).isTrue();
                } else {
                    assertThat(removed).isFalse();
                }
                if (removed) {
                    if (present.getOrDefault(-n, 0) == 1)
                        present.remove(-n);
                    else
                        present.put(-n, present.getOrDefault(-n, 0) - 1);
                    --total;
                }
            }
            Map<Integer, Integer> freq = new HashMap<>();
            int sampleSize = total * 100000;
            for (int i = 0; i < sampleSize; ++i) {
                int random = idgr.getRandom();
                freq.put(random, freq.getOrDefault(random, 0) + 1);
            }
            for (int m : present.keySet()) {
                assertThat((float) freq.getOrDefault(m, 0) / sampleSize)
                        .isCloseTo((float) present.getOrDefault(m, 0) / total, withPercentage(1));
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
            {new int[] {+1, +1, +2}},
        };
    }
    
}
