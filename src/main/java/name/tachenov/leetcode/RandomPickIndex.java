/*
 * (C) Sergey A. Tachenov
 * This thing belongs to public domain. Really.
 */
package name.tachenov.leetcode;

import java.util.*;

/**
 *
 * @author Sergey A. Tachenov
 */
public class RandomPickIndex {
    private final Map<Integer, List<Integer>> indices = new HashMap<>();
    private final Random random = new Random();
    
    public RandomPickIndex(int[] nums) {
        for (int i = 0; i < nums.length; ++i) {
            List<Integer> ind = indices.get(nums[i]);
            if (ind == null) {
                ind = new ArrayList<>();
            }
            ind.add(i);
            indices.put(nums[i], ind);
        }
    }

    public int pick(int target) {
        List<Integer> ind = indices.get(target);
        return ind.get(random.nextInt(ind.size()));
    }
}
