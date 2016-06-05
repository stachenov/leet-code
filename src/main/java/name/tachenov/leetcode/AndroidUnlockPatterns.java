/*
 * (C) Sergey A. Tachenov
 * This thing belongs to public domain. Really.
 */
package name.tachenov.leetcode;

/**
 * Android Unlock Patterns #351.
 * @author Sergey A. Tachenov
 */
public class AndroidUnlockPatterns {
    private static final int HEIGHT = 3;
    private static final int WIDTH = 3;
    private int numberOfPatterns;

    public int numberOfPatterns(int m, int n) {
        numberOfPatterns = 0;
        for (int i = m; i <= n; ++i) {
            numberOfPatterns(i);
        }
        return numberOfPatterns;
    }
    
    private void numberOfPatterns(int n) {
        boolean[][] touched = new boolean[HEIGHT][WIDTH];
        for (int i = 0; i < HEIGHT; ++i) {
            for (int j = 0; j < WIDTH; ++j) {
                touched[i][j] = true;
                numberOfPatterns(touched, i, j, n - 1);
                touched[i][j] = false;
            }
        }
    }
    
    private void numberOfPatterns(boolean[][] touched, int lastRow, int lastColumn, int n) {
        if (n == 0) {
            ++numberOfPatterns;
            return;
        }
        for (int i = 0; i < HEIGHT; ++i) {
            for (int j = 0; j < WIDTH; ++j) {
                if (i == lastRow && j == lastColumn || touched[i][j]) {
                    continue;
                }
                if (isValidMove(touched, lastRow, lastColumn, i, j)) {
                    touched[i][j] = true;
                    numberOfPatterns(touched, i, j, n - 1);
                    touched[i][j] = false;
                }
            }
        }
    }
    
    private static boolean isValidMove(boolean[][] touched, int fromRow, int fromColumn, int toRow, int toColumn) {
        return !isStraightLine(fromRow, fromColumn, toRow, toColumn)
                || allIntermediatePointsAreTouched(touched, fromRow, fromColumn, toRow, toColumn);
    }

    private static boolean isStraightLine(int fromRow, int fromColumn, int toRow, int toColumn) {
        return toRow == fromRow || toColumn == fromColumn
                || Math.abs(toRow - fromRow) == Math.abs(toColumn - fromColumn);
    }

    private static boolean allIntermediatePointsAreTouched(boolean[][] touched, int fromRow, int fromColumn,
            int toRow, int toColumn) {
        int stepI = step(fromRow, toRow);
        int stepJ = step(fromColumn, toColumn);
        for (int i = fromRow, j = fromColumn; !(i == toRow && j == toColumn); i += stepI, j += stepJ) {
            if (!touched[i][j]) {
                return false;
            }
        }
        return true;
    }

    private static int step(int fromRow, int toRow) {
        return toRow == fromRow ? 0 : toRow > fromRow ? +1 : -1;
    }

}
