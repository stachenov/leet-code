/*
 * (C) Sergey A. Tachenov
 * This thing belongs to public domain. Really.
 */
package name.tachenov.leetcode;

import java.util.*;

public class SnakeGame {
    private final int width;
    private final int height;
    private final Queue<Point> food = new ArrayDeque<>();
    private final Snake snake = new Snake();
    
    /** Initialize your data structure here.
        @param width - screen width
        @param height - screen height 
        @param food - A list of food positions
        E.g food = [[1,1], [1,0]] means the first food is positioned at [1,1], the second is at [1,0]. */
    public SnakeGame(int width, int height, int[][] food) {
        this.width = width;
        this.height = height;
        for (int[] f : food) {
            this.food.add(new Point(f[1], f[0]));
        }
    }
    
    /** Moves the snake.
        @param direction - 'U' = Up, 'L' = Left, 'R' = Right, 'D' = Down 
        @return The game's score after the move. Return -1 if game over. 
        Game over when snake crosses the screen boundary or bites its body. */
    public int move(String direction) {
        Point dir = Point.fromString(direction);
        if (!snake.move(dir))
            return -1;
        return getScore();
    }

    private int getScore() {
        return snake.length() - 1;
    }
    
    private class Snake {
        private final Deque<Point> snake = new ArrayDeque<>();
        private final Set<Point> occupied = new HashSet<>();

        Snake() {
            Point initial = new Point(0, 0);
            addHead(initial);
        }

        private void addHead(Point head) {
            snake.addFirst(head);
            occupied.add(head);
        }

        boolean move(Point dir) {
            Point head = getHead();
            head = head.move(dir);
            if (isOutOfBounds(head))
                return false;
            if (head.equals(food.peek())) {
                food.remove();
            } else {
                removeTail();
            }
            if (occupied.contains(head))
                return false;
            addHead(head);
            return true;
        }

        private boolean isOutOfBounds(Point head) {
            return head.x < 0 || head.x >= width || head.y < 0 || head.y >= height;
        }

        private void removeTail() {
            Point tail = snake.removeLast();
            occupied.remove(tail);
        }

        Point getHead() {
            return snake.getFirst();
        }

        int length() {
            return snake.size();
        }
    }
    
    private static class Point {
        final int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
        
        static Point fromString(String direction) {
            switch (direction) {
                case "U":
                    return new Point(0, -1);
                case "D":
                    return new Point(0, +1);
                case "L":
                    return new Point(-1, 0);
                case "R":
                    return new Point(+1, 0);
                default:
                    throw new IllegalArgumentException(direction);
            }
        }

        Point move(Point dir) {
            return new Point(x + dir.x, y + dir.y);
        }

        @Override
        public int hashCode() {
            int hash = 7;
            hash = 23 * hash + this.x;
            hash = 23 * hash + this.y;
            return hash;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null || getClass() != obj.getClass())
                return false;
            final Point other = (Point) obj;
            return this.x == other.x && this.y == other.y;
        }

        @Override
        public String toString() {
            return "Point{" + "x=" + x + ", y=" + y + '}';
        }
    }
}
