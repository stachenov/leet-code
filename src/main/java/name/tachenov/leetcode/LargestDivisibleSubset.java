/*
 * (C) Sergey A. Tachenov
 * This thing belongs to public domain. Really.
 */
package name.tachenov.leetcode;

import java.util.*;

public class LargestDivisibleSubset {

    public List<Integer> largestDivisibleSubset(int[] nums) {
        Arrays.sort(nums);
        int[] parent = new int[nums.length];
        Arrays.fill(parent, -1);
        int[] len = new int[nums.length];
        int iLongest = -1, lenLongest = -1;
        for (int i = 0; i < nums.length; ++i) {
            for (int j = i + 1; j < nums.length; ++j) {
                if (nums[j] % nums[i] == 0 && len[i] + 1 > len[j]) {
                    parent[j] = i;
                    len[j] = len[i] + 1;
                }
            }
            if (len[i] > lenLongest) {
                iLongest = i;
                lenLongest = len[i];
            }
        }
        List<Integer> result = new ArrayList<>();
        for (int i = iLongest; i != -1; i = parent[i]) {
            result.add(nums[i]);
        }
        return result;
    }
}
