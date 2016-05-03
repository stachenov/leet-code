/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package name.tachenov.leetcode;

import java.util.stream.IntStream;

/**
 *
 * @author alqualos
 */
public class Median2Removal {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        //Arrays.
        int[][] m = findMedianSortedArrays(nums1, 0, nums1.length,
                                           nums2, 0, nums2.length,
                                           0, 0);
        if (m.length == 1) {
            return m[0][0];
        } else {
            return ((double) m[0][0] + m[1][0]) / 2.0;
        }
    }
    
    private static int[][] findMedianSortedArrays(int[] nums1, int start1, int end1,
                                                  int[] nums2, int start2, int end2,
                                                  int removedLeft, int removedRight) {
        System.out.println("nums1: " + IntStream.of(nums1).skip(start1).limit(end1 - start1)
                .boxed().map(String::valueOf).reduce((s1, s2) -> s1 + "," + s2).orElse(""));
        System.out.println("nums2: " + IntStream.of(nums2).skip(start2).limit(end2 - start2)
                .boxed().map(String::valueOf).reduce((s1, s2) -> s1 + "," + s2).orElse(""));
        if (end1 - start1 <= 2 && end2 - start2 <= 2) {
            int[] merged = merge(nums1, start1, end1, nums2, start2, end2);
            int startMerged = 0, endMerged = merged.length;
            while (removedLeft + startMerged < removedRight) {
                ++startMerged;
            }
            while (removedLeft > removedRight + (merged.length - endMerged)) {
                --endMerged;
            }
            return median(merged, startMerged, endMerged);
        }
        int[][] med1 = median(nums1, start1, end1);
        int[][] med2 = median(nums2, start2, end2);
        int diff = medianCompare(med1, med2);
        if (diff == 0) {
            return med1;
        } else if (diff < 0) {
            int newEnd2 = med2.length == 1 ? med2[0][1] + 1 : med2[1][1] + 1;
            return findMedianSortedArrays(nums1, med1[0][1], end1,
                                          nums2, start2, newEnd2,
                                          removedLeft + (med1[0][1] - start1),
                                          removedRight + (end2 - newEnd2));
        } else {
            int newEnd1 = med1.length == 1 ? med1[0][1] + 1 : med1[1][1] + 1;
            return findMedianSortedArrays(nums1, start1, newEnd1,
                                          nums2, med2[0][1], end2,
                                          removedLeft + (med2[0][1] - start2),
                                          removedRight + (end1 - newEnd1));
        }
    }

    static int[] merge(int[] nums1, int start1, int end1, int[] nums2, int start2, int end2) {
        int[] merged = new int[end1 - start1 + end2 - start2];
        for (int i = 0, j = 0, k = 0; k < merged.length; ++k) {
            if (i >= end1 - start1) {
                merged[k] = nums2[start2 + j];
                ++j;
            } else if (j >= end2 - start2 || nums1[start1 + i] < nums2[start2 + j]) {
                merged[k] = nums1[start1 + i];
                ++i;
            } else {
                merged[k] = nums2[start2 + j];
                ++j;
            }
        }
        return merged;
    }
    
    private static int[][] median(int[] nums, int start, int end) {
        final int len = end - start, mid = start + len / 2;
        if (len % 2 == 0) {
            return new int[][] {{nums[mid - 1], mid - 1}, {nums[mid], mid}};
        } else {
            return new int[][] {{nums[mid], mid}};
        }
    }
    
    private static int medianCompare(int[][] med1, int[][] med2) {
        // Use Long.compare to avoid integer over/underflows!
        if (med1.length == 1) {
            if (med2.length == 1) {
                return Integer.compare(med1[0][0], med2[0][0]);
            } else {
                return Long.compare((long) med1[0][0] - med2[0][0],
                                    (long) med2[1][0] - med1[0][0]);
            }
        } else {
            if (med2.length == 1) {
                return Long.compare((long) med1[1][0] - med2[0][0],
                                    (long) med2[0][0] - med1[0][0]);
            } else {
                return Long.compare((long) med1[0][0] - med2[0][0],
                                    (long) med2[1][0] - med1[1][0]);
            }
        }
    }
    
    public static void main(String[] args) {
        Median2Removal med2 = new Median2Removal();
//        System.out.println(med2.findMedianSortedArrays(new int[] {1, 2, 10, 12},
//                new int[] {0, 3, 15}));
        //System.out.println(med2.findMedianSortedArrays(new int[] {},
        //        new int[] {1, 2, 3, 4}));
        System.out.println(med2.findMedianSortedArrays(new int[] {1}, new int[] {2,3,4,5,6}));
    }
}
