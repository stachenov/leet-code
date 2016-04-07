/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package name.tachenov.leetcode;

/**
 *
 * @author alqualos
 */
public class BuggyDuplicate2 {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        if (nums.length == 0)
            return false;
        int[] indices = new int[nums.length];
        for (int i = 0; i < nums.length; ++i) {
            indices[i] = i;
        }
        quicksort(nums, indices, 0, nums.length);
        for (int i = 1; i < nums.length; ++i) {
            if (nums[i - 1] == nums[i] && Math.abs(indices[i - 1] - indices[i]) <= k)
                return true;
        }
        return false;
    }
    
    private void quicksort(int[] nums, int[] indices, int start, int end) {
        if (end - start > 1) {
            int p = partition(nums, indices, start, end);
            quicksort(nums, indices, start, p);
            quicksort(nums, indices, p + 1, end);
        }
    }
    
    private int partition(int[] nums, int[] indices, int start, int end) {
        int p = start + (end - start) / 2;
        int pivot = nums[p];
        for (int i = start, j = end - 1; ; ) {
            while (nums[i] < pivot) {
                ++i;
            }
            while (nums[j] > pivot) {
                --j;
            }
            if (i < j && nums[i] != nums[j]) {
                nums[i] ^= nums[j];
                indices[i] ^= indices[j];
                nums[j] ^= nums[i];
                indices[j] ^= indices[i];
                nums[i] ^= nums[j];
                indices[i] ^= indices[j];
            } else {
                return i;
            }
        }
    }
    
    public static void main(String[] args) {
        int[] nums = new int[14];
        for (int i = 0; i < 14; ++i) {
            nums[i] = i;
        }
        nums[13] = nums[5] = nums[6] = 0;
        System.out.println(new BuggyDuplicate2().containsNearbyDuplicate(nums, 1));
    }
}
