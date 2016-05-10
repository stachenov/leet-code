/*
 * (C) Sergey A. Tachenov
 * This thing belongs to public domain. Really.
 */
package name.tachenov.leetcode;

/**
 * TicTacToe (problem #348).
 * @author Sergey A. Tachenov
 */
public class TicTacToe {
    
    public static final int X = 1;
    public static final int O = 2;
    private final int[][] rowMoveCount;
    private final int[][] columnMoveCount;
    private final int[] mainDiagonalMoveCount;
    private final int[] counterDiagonalMoveCount;

    /** Initialize your data structure here.
     * @param n size of the field
     */
    public TicTacToe(int n) {
        rowMoveCount = new int[2][n];
        columnMoveCount = new int[2][n];
        mainDiagonalMoveCount = new int[2];
        counterDiagonalMoveCount = new int[2];
    }
    
    /** Player {player} makes a move at ({row}, {col}).
        @param row The row of the board.
        @param col The column of the board.
        @param player The player, can be either 1 or 2.
        @return The current winning condition, can be either:
                0: No one wins.
                1: Player 1 wins.
                2: Player 2 wins. */
    public int move(int row, int col, int player) {
        ++rowMoveCount[player - 1][row];
        ++columnMoveCount[player - 1][col];
        if (row == col) {
            ++mainDiagonalMoveCount[player - 1];
        }
        if (row == getFieldSize() - 1 - col) {
            ++counterDiagonalMoveCount[player - 1];
        }
        if (rowMoveCount[player - 1][row] == getFieldSize()
                || columnMoveCount[player - 1][col] == getFieldSize()
                || mainDiagonalMoveCount[player - 1] == getFieldSize()
                || counterDiagonalMoveCount[player - 1] == getFieldSize()) {
            return player;
        }
        return 0;
    }
    
    private int getFieldSize() {
        return rowMoveCount[0].length;
    }
}
