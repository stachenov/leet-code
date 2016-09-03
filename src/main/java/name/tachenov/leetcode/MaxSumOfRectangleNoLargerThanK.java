/*
 * (C) Sergey A. Tachenov
 * This thing belongs to public domain. Really.
 */
package name.tachenov.leetcode;

import java.util.*;

public class MaxSumOfRectangleNoLargerThanK {
    public int maxSumSubmatrix(int[][] matrix, int k) {
        RangeSum2D rangeSums = new RangeSum2D(matrix);
        int max = Integer.MIN_VALUE;
        for (int width = 1; width <= matrix[0].length; ++width) {
            for (int j = 0; j <= matrix[0].length - width; ++j) {
                NavigableSet<Integer> minusSums = new TreeSet<>();
                minusSums.add(0);
                for (int i = 0; i < matrix.length; ++i) {
                    int sum = rangeSums.rectangleSum(0, j, i + 1, width);
                    // sum + minusSum should be max, but <= k
                    Integer minusSum = minusSums.floor(k - sum);
                    if (minusSum != null) {
                        max = Math.max(max, minusSum + sum);
                        if (max == k)
                            return k;
                    }
                    minusSums.add(-sum);
                }
            }
        }
        return max;
    }
    
    private static class RangeSum2D {
        final int[][] submatrixSum;

        RangeSum2D(int[][] matrix) {
            this.submatrixSum = new int[matrix.length + 1][matrix[0].length + 1];
            for (int i = 0; i < matrix.length; ++i) {
                for (int j = 0; j < matrix[i].length; ++j) {
                    submatrixSum[i + 1][j + 1]
                            = submatrixSum[i][j + 1]
                            + submatrixSum[i + 1][j]
                            + matrix[i][j]
                            - submatrixSum[i][j];
                }
            }
        }
        
        int rectangleSum(int row, int col, int height, int width) {
            return submatrixSum[row + height][col + width]
                 - submatrixSum[row][col + width]
                 - submatrixSum[row + height][col]
                 + submatrixSum[row][col];
        }
    }
}
