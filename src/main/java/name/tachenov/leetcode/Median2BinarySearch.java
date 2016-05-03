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
public class Median2BinarySearch {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length == 0) {
            return singleArrayMedian(nums2);
        } else if (nums2.length == 0) {
            return singleArrayMedian(nums1);
        }
        final int k = nums1.length + nums2.length;
        int[] smaller = nums1.length <= nums2.length ? nums1 : nums2;
        int[] bigger = smaller == nums1 ? nums2 : nums1;
        int[] median = findMedian(smaller, bigger, (k - 1) / 2 + 1);
        int m1 = median[0] >= 0 ? smaller[median[0]] : bigger[median[1]];
        if (k % 2 != 0)
            return m1;
        // For even lengths, we need to find the next median.
        // It should be somewhere nearby!
        int i1 = median[0] >= 0 ? median[0] + 1 : -median[0] - 1;
        int i2 = median[1] >= 0 ? median[1] + 1: -median[1] - 1;
        int m2;
        if (i1 == smaller.length) {
            m2 = bigger[i2];
        } else if (i2 == bigger.length) {
            m2 = smaller[i1];
        } else {
            m2 = Math.min(smaller[i1], bigger[i2]);
        }
        return ((double) m1 + m2) / 2.0;
    }

    /**
     * Finds median or one of the medians. The median coordinate is given
     * by the {@code leCount} parameter which can be though of as 1-based
     * index of the median (like in the find kth element problem).
     * The returning value contains two values: first for the {@code nums1}
     * array, second for the {@code nums2} one. One of the values is a
     * non-negative index indicating where the median was found. The other one
     * is an insertion point like in binary search, that is, {@code -index - 1}.
     * Unlike binary search, however, the median may actually be present at
     * that point too, the algorithm just doesn't check that.
     * 
     * @param nums1 the first array
     * @param nums2 the second array
     * @param leCount the number of the elements in the merged array that
     * are less than or equal to the median
     * @return an array of one index and one insertion point
     */
    private int[] findMedian(int[] nums1, int[] nums2, int leCount) {
        int s1 = 0, e1 = nums1.length - 1;
        while (s1 <= e1) {
            int mid1 = (s1 + e1) / 2;
            // We have exactly mid1 + 1 elements in the first array that
            // are less than or equal to nums1[mid1] (including itself).
            // If mid1 is the median, then there must be exactly
            // leCount - (mid1 + 1) elements in the second array that
            // are less than or equal to the would-be median.
            // All subsequent elements must be greater than or equal,
            // so they can be put into the right half during the merge.
            int le2 = leCount - (mid1 + 1);
            assert le2 >= 0 && le2 <= nums2.length;
            if ((le2 == 0 || nums2[le2 - 1] <= nums1[mid1])
                && (le2 == nums2.length || nums2[le2] >= nums1[mid1])) {
                return new int[] {mid1, -le2 - 1};
            } else if (le2 > 0 && nums2[le2 - 1] > nums1[mid1]) {
                // missed, the median must be to the right
                s1 = mid1 + 1;
            } else {
                // or to the left
                e1 = mid1 - 1;
            }
        }
        // Haven't found the median in the first array, but we now know
        // where it would be if it was there. That means we know exactly
        // how many elements less than the median are here. That gives
        // us the exact answer where the median is in the second array!
        int mid2 = leCount - s1 - 1;
        assert mid2 >= 0 && mid2 < nums2.length;
        return new int[] {-s1 - 1, mid2};
    }
    
    private static double singleArrayMedian(int[] nums) {
        if (nums.length % 2 == 0) {
            return ((double) nums[nums.length / 2 - 1] + nums[nums.length / 2]) / 2.0;
        } else {
            return nums[nums.length / 2];
        }
    }
    
    public static void main(String[] args) {
        Median2BinarySearch me = new Median2BinarySearch();
        //System.out.println(me.findMedianSortedArrays(new int[] {0, 1, 4, 10}, new int[] {3}));
        System.out.println(me.findMedianSortedArrays(new int[] {1, 1}, new int[] {1, 2}));
        //System.out.println(me.findMedianSortedArrays(new int[] {1, 1, 4, 4}, new int[] {0, 2, 3, 10, 12}));
    }
}
