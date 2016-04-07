package name.tachenov.leetcode;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author alqualos
 */
public class NumMatrix {
    
    private final int[][] matrix;
    private final int[][] bit2d;

    public NumMatrix(int[][] matrix) {
        this.matrix = new int[matrix.length][];
        if (matrix.length > 0) {
            bit2d = new int[matrix.length][matrix[0].length];
        } else {
            bit2d = new int[matrix.length][];
        }
        for (int i = 0; i < matrix.length; ++i) {
            this.matrix[i] = new int[matrix[i].length];
            for (int j = 0; j < matrix[i].length; ++j) {
                update(i, j, matrix[i][j]);
            }
        }
    }

    public final void update(int row, int col, int val) {
        for (int i = row + 1; i <= bit2d.length; i += i & -i) {
            for (int j = col + 1; j <= bit2d[i - 1].length; j += j & -j) {
                bit2d[i - 1][j - 1] += val - matrix[row][col];
            }
        }
        matrix[row][col] = val;
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        int sum = 0;
        for (int i = row2 + 1; i >= 1; i -= i & -i) {
            for (int j = col2 + 1; j >= 1; j -= j & -j) {
                sum += bit2d[i - 1][j - 1];
            }
        }
        for (int i = row2 + 1; i >= 1; i -= i & -i) {
            for (int j = col1; j >= 1; j -= j & -j) {
                sum -= bit2d[i - 1][j - 1];
            }
        }
        for (int i = row1; i >= 1; i -= i & -i) {
            for (int j = col2 + 1; j >= 1; j -= j & -j) {
                sum -= bit2d[i - 1][j - 1];
            }
        }
        for (int i = row1; i >= 1; i -= i & -i) {
            for (int j = col1; j >= 1; j -= j & -j) {
                sum += bit2d[i - 1][j - 1];
            }
        }
        return sum;
    }
    
    public static void main(String[] args) {
        NumMatrix nm = new NumMatrix(LeetCode.array2d("  [[3, 0, 1, 4, 2],\n" +
"  [5, 6, 3, 2, 1],\n" +
"  [1, 2, 0, 1, 5],\n" +
"  [4, 1, 0, 1, 7],\n" +
"  [1, 0, 3, 0, 5]]"));
        System.out.println(nm.sumRegion(2, 1, 4, 3));
        nm.update(3, 2, 2);
        System.out.println(nm.sumRegion(2, 1, 4, 3));
        //System.out.println(nm.sumRegion(0, 0, 1, 1));
    }
}
