/*
 * (C) Sergey A. Tachenov
 * This thing belongs to public domain. Really.
 */
package name.tachenov.leetcode;

public class MaxSumOfRectangleNoLargerThanK {
    public int maxSumSubmatrix(int[][] matrix, int k) {
        final int m = matrix.length;
        final int n = matrix[0].length;
        RangeSum2D rangeSums = new RangeSum2D(matrix);
        int max = Integer.MIN_VALUE;
        for (int width = 1; width <= n; ++width) {
            for (int j = 0; j <= n - width; ++j) {
                long[] verticalPrefixSum = new long[m + 1];
                for (int height = 0; height <= m; ++height)
                    verticalPrefixSum[height] = rangeSums.rectangleSum(0, j, height, width);
                max = Math.max(max, (int) mergeSort(verticalPrefixSum, k));
                if (max == k)
                    return k;
            }
        }
        return max;
    }

    private static long mergeSort(long[] prefixSums, int k) {
        return mergeSort(prefixSums, 0, prefixSums.length, k);
    }

    private static long mergeSort(long[] prefixSums, int start, int end, int k) {
        if (end - start <= 1)
            return Integer.MIN_VALUE;
        int mid = (start + end) >>> 1;
        long max = Math.max(mergeSort(prefixSums, start, mid, k),
                            mergeSort(prefixSums, mid, end, k));
        for (int left = start, right = mid + 1; left < mid; ++left) {
            while (right < end && prefixSums[right] - prefixSums[left] <= k)
                ++right;
            long rangeSum = prefixSums[right - 1] - prefixSums[left];
            if (rangeSum <= k)
                max = Math.max(max, rangeSum);
            if (max == k)
                return k;
        }
        merge(prefixSums, start, mid, end);
        return max;
    }

    private static void merge(long[] prefixSums, int start, int mid, int end) {
        long[] temp = new long[end - start];
        int left = start, right = mid, out = 0;
        while (left < mid && right < end) {
            if (prefixSums[left] <= prefixSums[right])
                temp[out++] = prefixSums[left++];
            else
                temp[out++] = prefixSums[right++];
        }
        if (left < end)
            System.arraycopy(prefixSums, left, temp, out, mid - left);
        if (right < end)
            System.arraycopy(prefixSums, right, temp, out, end - right);
        System.arraycopy(temp, 0, prefixSums, start, end - start);
    }
    
    private static class RangeSum2D {
        final long[][] topLeftSubmatrixSum;

        RangeSum2D(int[][] matrix) {
            final int m = matrix.length;
            final int n = matrix[0].length;
            this.topLeftSubmatrixSum = new long[m + 1][n + 1];
            for (int i = 0; i < m; ++i) {
                for (int j = 0; j < n; ++j) {
                    topLeftSubmatrixSum[i + 1][j + 1]
                            = topLeftSubmatrixSum[i][j + 1]
                            + topLeftSubmatrixSum[i + 1][j]
                            + matrix[i][j]
                            - topLeftSubmatrixSum[i][j];
                }
            }
        }
        
        long rectangleSum(int row, int col, int height, int width) {
            return topLeftSubmatrixSum[row + height][col + width]
                 - topLeftSubmatrixSum[row][col + width]
                 - topLeftSubmatrixSum[row + height][col]
                 + topLeftSubmatrixSum[row][col];
        }
    }
}
