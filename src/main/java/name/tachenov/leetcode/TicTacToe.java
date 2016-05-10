/*
 * (C) Sergey A. Tachenov
 * This thing belongs to public domain. Really.
 */
package name.tachenov.leetcode;

import java.util.stream.IntStream;

/**
 * TicTacToe (problem #348).
 * @author Sergey A. Tachenov
 */
public class TicTacToe {
    
    public static final int X = 1;
    public static final int O = 2;
    private final int[][] field;

    /** Initialize your data structure here.
     * @param n size of the field
     */
    public TicTacToe(int n) {
        field = new int[n][n];
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
        field[row][col] = player;
        if (IntStream.range(0, field.length).map(i -> field[i][col]).allMatch(f -> f == player)
                || IntStream.range(0, field.length).map(j -> field[row][j]).allMatch(f -> f == player)) {
            return player;
        }
        if (row == col) { // main diagonal
            if (IntStream.range(0, field.length).map(i -> field[i][i]).allMatch(f -> f == player)) {
                return player;
            }
        }
        if (row == field.length - 1 - col) { // counter diagonal
            if (IntStream.range(0, field.length).map(i -> field[i][field.length - 1 - i]).allMatch(f -> f == player)) {
                return player;
            }
        }
        return 0;
    }
}
