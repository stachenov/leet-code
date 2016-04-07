/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package name.tachenov.leetcode;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 *
 * @author alqualos
 */
public class MaxNumber {
    public int[] maxNumber(int[] nums1, int[] nums2, final int k) {
        if (k > nums1.length + nums2.length)
            throw new IllegalArgumentException("k=" + k + " n=" + nums1.length + " m=" + nums2.length);
        int[][] digitPositions1 = findDigitPositions(nums1);
        int[][] digitPositions2 = findDigitPositions(nums2);
        Merger best = null;
        final int l1start = nums2.length >= k ? 0 : k - nums2.length;
        final int l1end = Math.min(k, nums1.length);
        // Will now try to get the largest sequences of digits from nums1, nums2 of lengths l1, (k - l1)
        // and merge them. The largest merge result will be the answer.
        for (int l1 = l1start; l1 <= l1end; ++l1) {
            MaxFinder res1 = new MaxFinder(nums1, l1, k, digitPositions1);
            MaxFinder res2 = new MaxFinder(nums2, k - l1, k, digitPositions2);
            Merger merger = new Merger(res1, res2, k);
            if (best == null || merger.compareTo(best) > 0) {
                best = merger;
            }
        }
        assert best != null;
        return best.toIntArray();
    }

    /**
     * Computes the nearest positions of the each digit. The returned array contains at position [i][d]
     * the answer to the question
     * "What is the next index of digit {@code d} if we were to search starting at position {@code i}?"
     * Here, ({@code 0 <= d <= 9, 0 <= i < nums.length}) and the answer -1 means
     * "no more occurences of that digit from position {@code i} onwards".
     * 
     * @param nums the input array containing a sequence of digits
     * @return an array containing digit indexes
     */
    private static int[][] findDigitPositions(int[] nums) {
        int[][] digitPositions = new int[nums.length][];
        int[] pos = new int[10];
        Arrays.fill(pos, -1);
        for (int i = nums.length - 1; i >= 0; --i) {
            pos[nums[i]] = i;
            digitPositions[i] = Arrays.copyOf(pos, pos.length);
        }
        return digitPositions;
    }
    
    /**
     * Lazily merges two sequences of digits into one sequence of length k.
     */
    private static class Merger implements Comparable<Merger> {
        private final MaxFinder seq1;
        private final MaxFinder seq2;
        private final int k; // total length
        private final int[] output;
        private int outPos = 0, inPos1 = 0, inPos2 = 0;

        Merger(MaxFinder seq1, MaxFinder seq2, int k) {
            this.seq1 = seq1;
            this.seq2 = seq2;
            this.k = k;
            this.output = new int[k];
        }
        
        int get(int i) {
            while (outPos <= i) {
                if (seq1.compareTo(seq2) >= 0) {
                    output[outPos++] = seq1.get(inPos1++);
                } else {
                    output[outPos++] = seq2.get(inPos2++);
                }
            }
            return output[i];
        }
        
        @Override
        public int compareTo(Merger that) {
            for (int i = 0; i < k; ++i) {
                int v1 = this.get(i), v2 = that.get(i);
                if (v1 > v2) {
                    return +1;
                } else if (v1 < v2) {
                    return -1;
                }
            }
            return 0;
        }
        
        int[] toIntArray() {
            get(k - 1); // complete generation
            return output;
        }
    }
    
    /**
     * Lazily generates the largest sequence of digits of a given length from a given array.
     */
    private static class MaxFinder implements Comparable<MaxFinder> {
        private final int[] input;
        private final int k; // total length of two sequences, needed for comparison caching
        private final int[][] digitPositions; // computed by findDigitPositions
        private final int[] output;
        /** Contains at position [k + (i1 - i2)] the index in this sequence where
         * the difference with the other sequence was found, given that the search started
         * with the difference between starting positions equal to (i1 - i2). */
        private final int[] lastDiffPos;
        private int inPos = 0, outPos = 0;
        /** The position after the last get() call, excluding calls from compareTo().
         * This is where the comparison actually starts. */
        private int nextIndex = 0;
        
        MaxFinder(int[] nums, int length, int k, int[][] digitPositions) {
            this.input = nums;
            this.k = k;
            this.digitPositions = digitPositions;
            this.output = new int[length];
            this.lastDiffPos = new int[2 * k + 1];
        }
        
        /**
         * Gets the digit at position i, generating lazily the digits up to that position.
         * @param i the position, {@code 0 <= i < length}
         * @return the digit at position i
         */
        int get(int i) {
            this.nextIndex = i + 1; // If we need to compare with the other sequence, here is where we should start.
            return compute(i);
        }
        
        // helper for get and compareTo
        private int compute(int i) {
            while (outPos <= i) {
                // Look for the next best digit.
                for (int d = 9; d >= 0; --d) {
                    final int nextDigitPos = digitPositions[inPos][d];
                    // The digit must satisfy two conditions:
                    // 1. It must be out there somewhere (obviously).
                    // 2. There must be enough digits after it, or we may end up running out of digits too soon.
                    if (nextDigitPos != -1 && input.length - (nextDigitPos + 1) >= output.length - outPos - 1) {
                        output[outPos++] = d;
                        inPos = digitPositions[inPos][d] + 1;
                        break;
                    }
                }
            }
            return output[i];
        }
        
        @Override
        public int compareTo(MaxFinder that) {
            final int cacheIndex = k + (this.nextIndex - that.nextIndex);
            // We can use the cached position if there is one, but only if we haven't advanced past the last found
            // difference position (which is stored in the cache).
            if (lastDiffPos[cacheIndex] != 0 && lastDiffPos[cacheIndex] >= this.nextIndex) {
                int lastDiffPos1 = lastDiffPos[cacheIndex];
                int lastDiffPos2 = lastDiffPos1 + (that.nextIndex - this.nextIndex);
                if (lastDiffPos1 < this.output.length && lastDiffPos2 < that.output.length) {
                    return Integer.compare(this.output[lastDiffPos1], that.output[lastDiffPos2]);
                } else {
                    return Integer.compare(this.output.length - this.nextIndex, that.output.length - that.nextIndex);
                }
            }
            // No luck in the cache.
            final int end = Math.min(this.output.length - this.nextIndex, that.output.length - that.nextIndex);
            int result = 0;
            for (int i = 0; i < end; ++i) {
                result = this.compute(this.nextIndex + i) - that.compute(that.nextIndex + i);
                if (result != 0) {
                    lastDiffPos[cacheIndex] = this.nextIndex + i;
                    break;
                }
            }
            if (result == 0) {
                lastDiffPos[cacheIndex] = this.nextIndex + end;
                return Integer.compare(this.output.length - this.nextIndex, that.output.length - that.nextIndex);
            } else {
                return result;
            }
        }
    }
    
    public static void main(String[] args) {
        MaxNumber mn = new MaxNumber();
        int[] n = mn.maxNumber(LeetCode.array1d("8,9,7,3,5,9,1,0,8,5,3,0,9,2,7,4,8,9,8,1,0,2,0,2,7,2,3,5,4,7,4,1,4,0,1,4,2,1,3,1,5,3,9,3,9,0,1,7,0,6,1,8,5,6,6,5,0,4,7,2,9,2,2,7,6,2,9,2,3,5,7,4,7,0,1,8,3,6,6,3,0,8,5,3,0,3,7,3,0,9,8,5,1,9,5,0,7,9,6,8,5,1,9,6,5,8,2,3,7,1,0,1,4,3,4,4,2,4,0,8,4,6,5,5,7,6,9,0,8,4,6,1,6,7,2,0,1,1,8,2,6,4,0,5,5,2,6,1,6,4,7,1,7,2,2,9,8,9,1,0,5,5,9,7,7,8,8,3,3,8,9,3,7,5,3,6,1,0,1,0,9,3,7,8,4,0,3,5,8,1,0,5,7,2,8,4,9,5,6,8,1,1,8,7,3,2,3,4,8,7,9,9,7,8,5,2,2,7,1,9,1,5,5,1,3,5,9,0,5,2,9,4,2,8,7,3,9,4,7,4,8,7,5,0,9,9,7,9,3,8,0,9,5,3,0,0,3,0,4,9,0,9,1,6,0,2,0,5,2,2,6,0,0,9,6,3,4,1,2,0,8,3,6,6,9,0,2,1,6,9,2,4,9,0,8,3,9,0,5,4,5,4,6,1,2,5,2,2,1,7,3,8,1,1,6,8,8,1,8,5,6,1,3,0,1,3,5,6,5,0,6,4,2,8,6,0,3,7,9,5,5,9,8,0,4,8,6,0,8,6,6,1,6,2,7,1,0,2,2,4,0,0,0,4,6,5,5,4,0,1,5,8,3,2,0,9,7,6,2,6,9,9,9,7,1,4,6,2,8,2,5,3,4,5,2,4,4,4,7,2,2,5,3,2,8,2,2,4,9,8,0,9,8,7,6,2,6,7,5,4,7,5,1,0,5,7,8,7,7,8,9,7,0,3,7,7,4,7,2,0,4,1,1,9,1,7,5,0,5,6,6,1,0,6,9,4,2,8,0,5,1,9,8,4,0,3,1,2,4,2,1,8,9,5,9,6,5,3,1,8,9,0,9,8,3,0,9,4,1,1,6,0,5,9,0,8,3,7,8,5"),
                LeetCode.array1d("7,8,4,1,9,4,2,6,5,2,1,2,8,9,3,9,9,5,4,4,2,9,2,0,5,9,4,2,1,7,2,5,1,2,0,0,5,3,1,1,7,2,3,3,2,8,2,0,1,4,5,1,0,0,7,7,9,6,3,8,0,1,5,8,3,2,3,6,4,2,6,3,6,7,6,6,9,5,4,3,2,7,6,3,1,8,7,5,7,8,1,6,0,7,3,0,4,4,4,9,6,3,1,0,3,7,3,6,1,0,0,2,5,7,2,9,6,6,2,6,8,1,9,7,8,8,9,5,1,1,4,2,0,1,3,6,7,8,7,0,5,6,0,1,7,9,6,4,8,6,7,0,2,3,2,7,6,0,5,0,9,0,3,3,8,5,0,9,3,8,0,1,3,1,8,1,8,1,1,7,5,7,4,1,0,0,0,8,9,5,7,8,9,2,8,3,0,3,4,9,8,1,7,2,3,8,3,5,3,1,4,7,7,5,4,9,2,6,2,6,4,0,0,2,8,3,3,0,9,1,6,8,3,1,7,0,7,1,5,8,3,2,5,1,1,0,3,1,4,6,3,6,2,8,6,7,2,9,5,9,1,6,0,5,4,8,6,6,9,4,0,5,8,7,0,8,9,7,3,9,0,1,0,6,2,7,3,3,2,3,3,6,3,0,8,0,0,5,2,1,0,7,5,0,3,2,6,0,5,4,9,6,7,1,0,4,0,9,6,8,3,1,2,5,0,1,0,6,8,6,6,8,8,2,4,5,0,0,8,0,5,6,2,2,5,6,3,7,7,8,4,8,4,8,9,1,6,8,9,9,0,4,0,5,5,4,9,6,7,7,9,0,5,0,9,2,5,2,9,8,9,7,6,8,6,9,2,9,1,6,0,2,7,4,4,5,3,4,5,5,5,0,8,1,3,8,3,0,8,5,7,6,8,7,8,9,7,0,8,4,0,7,0,9,5,8,2,0,8,7,0,3,1,8,1,7,1,6,9,7,9,7,2,6,3,0,5,3,6,0,5,9,3,9,1,1,0,0,8,1,4,3,0,4,3,7,7,7,4,6,4,0,0,5,7,3,2,8,5,1,4,5,8,5,6,7,5,7,3,3,9,6,8,1,5,1,1,1,0,3"), 500);
        //int[] n = mn.maxNumber(new int[] {3,3,3,2,3,7,3,8,6,0,5,0,7,8,9,2,9,6,6,9,9,7,9,7,6,1,7,2,7,5,5,1},
        //                       new int[] {5,6,4,9,6,9,2,2,7,5,4,3,0,0,1,7,1,8,1,5,2,5,7,0,4,3,8,7,3,8,5,3,8,3,4,0,2,3,8,2,7,1,2,3,8,7,6,7,1,1,3,9,0,5,2,8,2,8,7,5,0,8,0,7,2,8,5,6,5,9,5,1,5,2,6,2,4,9,9,7,6,5,7,9,2,8,8,3,5,9,5,1,8,8,4,6,6,3,8,4,6,6,1,3,4,1,6,7,0,8,0,3,3,1,8,2,2,4,5,7,3,7,7,4,3,7,3,0,7,3,0,9,7,6,0,3,0,3,1,5,1,4,5,2,7,6,2,4,2,9,5,5,9,8,4,2,3,6,1,9},
        //                       160);
        //int[] n = mn.maxNumber(new int[] {3, 9}, new int[] {8, 9}, 3);
        //int[] n = mn.maxNumber(new int[] {2,5,6,4,4,0}, new int[] {7,3,8,0,6,5,7,6,2}, 15);
        //int[] n = mn.maxNumber(new int[] {8, 9}, new int[] {3, 9}, 3);
        //int[] n = mn.maxNumber(new int[] {1, 2}, new int[] {}, 2);
        //int[] n = mn.maxNumber(new int[] {6,3,9,0,5,6}, new int[] {2,2,5,2,1,4,4,5,7,8,9,3,1,6,9,7,0}, 23);
        System.out.println(IntStream.of(n).mapToObj(String::valueOf).reduce((s1, s2) -> s1 + "," + s2).orElse(""));
        System.out.println(Arrays.equals(new int[] {9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,8,8,8,8,5,3,6,4,2,6,3,6,7,6,6,9,5,4,3,2,7,6,3,1,8,7,5,7,8,1,6,0,7,3,0,4,4,4,9,6,3,1,0,3,7,3,6,1,0,0,2,5,7,2,9,6,6,2,6,8,1,9,7,8,8,9,5,1,1,4,2,0,1,3,6,7,8,7,0,5,6,0,1,7,9,6,4,8,6,7,0,2,3,2,7,6,0,5,0,9,0,3,3,8,5,0,9,3,8,0,1,3,1,8,1,8,1,1,7,5,7,4,1,0,0,0,8,9,5,7,8,9,2,8,3,0,3,4,9,8,1,7,2,3,8,3,5,3,1,4,7,7,5,4,9,2,6,2,6,4,0,0,2,8,3,3,0,9,1,6,8,3,1,7,0,7,1,5,8,3,2,5,1,1,0,3,1,4,6,3,6,2,8,6,7,2,9,5,9,1,6,0,5,4,8,6,6,9,4,0,5,8,7,0,8,9,7,3,9,0,1,0,6,2,7,3,3,2,3,3,6,3,0,8,0,0,5,2,1,0,7,5,0,3,2,6,0,5,4,9,6,7,1,0,4,0,9,6,8,3,1,2,5,0,1,0,6,8,6,6,8,8,2,4,5,0,0,8,0,5,6,2,2,5,6,3,7,7,8,4,8,4,8,9,1,6,8,9,9,0,4,0,5,5,4,9,6,7,7,9,0,5,0,9,2,5,2,9,8,9,7,6,8,6,9,2,9,1,6,0,2,7,4,4,5,3,4,5,5,5,0,8,1,3,8,3,0,8,5,7,6,8,7,8,9,7,0,8,4,0,7,0,9,5,8,2,0,8,7,0,3,1,8,1,7,1,6,9,7,9,7,2,6,3,0,5,3,6,0,5,9,3,9,1,1,0,0,8,1,4,3,0,4,3,7,7,7,4,6,4,0,0,5,7,3,2,8,5,1,4,5,8,5,6,7,5,7,3,3,9,6,8,1,5,1,1,1,0,3}, n));
    }
}
