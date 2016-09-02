/*
 * (C) Sergey A. Tachenov
 * This thing belongs to public domain. Really.
 */
package name.tachenov.leetcode;

import java.util.*;

public class BombEnemy {
    public int maxKilledEnemies(char[][] grid) {
        if (grid.length == 0)
            return 0;
        int[][] rowEnemies = new int[grid.length][grid[0].length];
        int[][] colEnemies = new int[grid.length][grid[0].length];
        scanRows(grid, rowEnemies, colEnemies);
        int max = 0;
        for (int i = 0; i < grid.length; ++i) {
            for (int j = 0; j < grid[i].length; ++j) {
                if (grid[i][j] == '0')
                    max = Math.max(max, rowEnemies[i][j] + colEnemies[i][j]);
            }
        }
        return max;
    }

    private void scanRows(char[][] grid, int[][] rowEnemies, int[][] colEnemies) {
        final int m = grid.length;
        final int n = grid[0].length;
        int[] nColEnemies = new int[n];
        int[] colLastWall = new int[n];
        Arrays.fill(colLastWall, -1);
        for (int row = 0; row <= m; ++row) {
            int rowLastWall = -1;
            int nRowEnemies = 0;
            for (int col = 0; col <= n; ++col) {
                char c = row < m && col < n ? grid[row][col] : 'W';
                if (c == 'E') {
                    ++nRowEnemies;
                    ++nColEnemies[col];
                } else if (c == 'W') {
                    for (int j = rowLastWall + 1; j < col; ++j) {
                        rowEnemies[row][j] = nRowEnemies;
                    }
                    nRowEnemies = 0;
                    rowLastWall = col;
                    if (col < n) {
                        for (int i = colLastWall[col] + 1; i < row; ++i) {
                            colEnemies[i][col] = nColEnemies[col];
                        }
                        nColEnemies[col] = 0;
                        colLastWall[col] = row;
                    }
                }
            }
        }
    }
}
