/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package name.tachenov.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 *
 * @author alqualos
 */
public class LongestConsecutive {
    public int longestConsecutive(int[] nums) {
        Map<Integer, Integer> ids = new HashMap<>();
        int[] parents = new int[nums.length];
        int[] ranks = new int[nums.length];
        int[] counts = new int[nums.length];
        boolean[] used = new boolean[nums.length];
        int maxCount = 0;
        for (int i = 0; i < nums.length; ++i) {
            final int n = nums[i];
            Integer id;
            if (used[Math.abs(n) % used.length]) {
                id = ids.get(n);
                if (id != null)
                    continue;
            }
            id = i;
            ids.put(n, i);
            used[Math.abs(n) % used.length] = true;
            parents[id] = id;
            counts[id] = 1;
            if (used[Math.abs(n - 1) % used.length]) {
                Integer left = ids.get(n - 1);
                if (left != null) {
                    id = union(parents, ranks, counts, left, id);
                }
            }
            if (used[Math.abs(n + 1) % used.length]) {
                Integer right = ids.get(n + 1);
                if (right != null) {
                    id = union(parents, ranks, counts, right, id);
                }
            }
            if (counts[id] > maxCount) {
                maxCount = counts[id];
            }
        }
        return maxCount;
    }
    
    private static int union(int[] parents, int[] ranks, int[] counts, int set, int n) {
        int root1 = find(parents, set);
        int root2 = find(parents, n);
        int rank1 = ranks[root1];
        int rank2 = ranks[root2];
        if (rank1 < rank2) {
            parents[root1] = root2;
            counts[root2] += counts[root1];
            return root2;
        } else if (rank2 < rank1) {
            parents[root2] = root1;
            counts[root1] += counts[root2];
            return root1;
        } else {
            parents[root2] = root1;
            ++ranks[root1];
            counts[root1] += counts[root2];
            return root1;
        }
    }
    
    private static int find(int[] parents, int key) {
        int parent = parents[key];
        if (parent != key) {
            parent = find(parents, parent);
            parents[key] = parent;
        }
        return parent;
    }
    
    public static void main(String[] args) {
        Random rnd = new Random(0);
        int[] nums = new int[1000];
        for (int i = 0; i < nums.length; ++i) {
            nums[i] = rnd.nextInt(100);
        }
        LongestConsecutive lc = new LongestConsecutive();
        for (int i = 0; i < nums.length; ++i) {
            lc.longestConsecutive(nums);
        }
    }
    
}
