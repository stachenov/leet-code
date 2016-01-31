/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package name.tachenov.leetcode;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 *
 * @author alqualos
 */
public class MaximalSquare {
    public int maximalSquare(char[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        List<Square> squares = new LinkedList<>();
        for (int i = 0; i < matrix.length; ++i) {
            for (int j = 0; j < matrix[i].length; ++j) {
                if (matrix[i][j] == '1') {
                    squares.add(new Square(i, j));
                }
            }
        }
        if (squares.isEmpty()) {
            return 0;
        }
        int size = 1;
        int k = Math.min(matrix.length, matrix[0].length);
        while (size <= k && !squares.isEmpty()) {
            for (ListIterator<Square> it = squares.listIterator(); it.hasNext(); ) {
                Square sq = it.next();
                boolean extended = true;
                if (sq.i + size < matrix.length && sq.j + size < matrix[sq.i].length) {
                    for (int j = sq.j; j <= sq.j + size; ++j) {
                        if (matrix[sq.i + size][j] == '0') {
                            extended = false;
                            break;
                        }
                    }
                    if (extended) {
                        for (int i = sq.i; i <= sq.i + size; ++i) {
                            if (matrix[i][sq.j + size] == '0') {
                                extended = false;
                                break;
                            }
                        }
                    }
                } else {
                    extended = false;
                }
                if (!extended) {
                    it.remove();
                }
            }
            if (!squares.isEmpty()) {
                ++size;
            }
        }
        return size * size;
    }
    
    private static class Square {
        final int i, j;

        Square(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }
    
    public static void main(String[] args) {
        MaximalSquare ms = new MaximalSquare();
        System.out.println(ms.maximalSquare(new char[][] {{'0', '1', '1', '1'}, {'1', '0', '1', '1'}, {'1', '0', '1', '1'}}));
    }
}
