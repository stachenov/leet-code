/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package name.tachenov.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 *
 * @author alqualos
 */
public class WallsAndGates {
    public void wallsAndGates(int[][] rooms) {
        for (int i = 0; i < rooms.length; ++i) {
            for (int j = 0; j < rooms[i].length; ++j) {
                if (rooms[i][j] == 0) {
                    searchForRooms(rooms, i, j);
                }
            }
        }
    }

    private static void searchForRooms(int[][] rooms, int i, int j) {
        Deque<Move> stack = new ArrayDeque<>();
        stack.push(new Move(i, j));
        while (!stack.isEmpty()) {
            Move m = stack.peek();
            if (m.hasNext()) {
                Move next = m.next(rooms, stack.size());
                if (next != null) {
                    stack.push(next);
                }
            } else {
                stack.pop();
            }
        }
    }
    
    private static class Move {
        private static final int DOWN = 0, UP = 1, RIGHT = 2, LEFT = 3, DONE = 4;
        final int i, j;
        int move = DOWN;
        
        Move(int i, int j) {
            this.i = i;
            this.j = j;
        }
        
        boolean hasNext() {
            return move != DONE;
        }
        
        Move next(int[][] rooms, int distance) {
            Move next = null;
            switch (move) {
                case DOWN:
                    next = i < rooms.length - 1 && rooms[i + 1][j] > distance ? new Move(i + 1, j) : null;
                    break;
                case UP:
                    next = i > 0 && rooms[i - 1][j] > distance ? new Move(i - 1, j) : null;
                    break;
                case RIGHT:
                    next = j < rooms[i].length - 1 && rooms[i][j + 1] > distance ? new Move(i, j + 1) : null;
                    break;
                case LEFT:
                    next = j > 0 && rooms[i][j - 1] > distance ? new Move(i, j - 1) : null;
                    break;
                default:
                    throw new AssertionError();
            }
            ++move;
            return next;
        }
    }
    
    public static void main(String[] args) {
        WallsAndGates wag = new WallsAndGates();
        wag.wallsAndGates(LeetCode.array2d("[[2147483647,-1,0,2147483647],[2147483647,2147483647,2147483647,-1],[2147483647,-1,2147483647,-1],[0,-1,2147483647,2147483647]]"));
    }
}
