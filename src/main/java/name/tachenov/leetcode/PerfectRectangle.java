/*
 * (C) Sergey A. Tachenov
 * This thing belongs to public domain. Really.
 */
package name.tachenov.leetcode;

import java.util.*;
import java.util.stream.*;

public class PerfectRectangle {
    public boolean isRectangleCover(int[][] rectangles) {
        int[] bounds = getBounds(rectangles);
        if (area(bounds) != totalArea(rectangles))
            return false;
        ToggleSet<Point> corners = findCornersOccurringOddTimes(rectangles);
        return corners.containsExactly(corners(bounds));
    }

    private int[] getBounds(int[][] rectangles) {
        int left = Integer.MAX_VALUE, right = Integer.MIN_VALUE,
                top = Integer.MIN_VALUE, bottom = Integer.MAX_VALUE;
        for (int i = 0; i < rectangles.length; ++i) {
            left = Math.min(left, left(rectangles[i]));
            right = Math.max(right, right(rectangles[i]));
            top = Math.max(top, top(rectangles[i]));
            bottom = Math.min(bottom, bottom(rectangles[i]));
        }
        return new int[] {left, bottom, right, top};
    }

    private static int left(int[] rectangle) {
        return rectangle[0];
    }

    private static int right(int[] rectangle) {
        return rectangle[2];
    }

    private static int top(int[] rectangle) {
        return rectangle[3];
    }

    private static int bottom(int[] rectangle) {
        return rectangle[1];
    }

    private int totalArea(int[][] rectangles) {
        return Stream.of(rectangles).mapToInt(r -> area(r)).sum();
    }
    
    private static int area(int[] rectangle) {
        return width(rectangle) * height(rectangle);
    }

    private static int width(int[] rectangle) {
        return right(rectangle) - left(rectangle);
    }

    private static int height(int[] rectangle) {
        return top(rectangle) - bottom(rectangle);
    }

    private ToggleSet<Point> findCornersOccurringOddTimes(int[][] rectangles) {
        ToggleSet<Point> corners = new ToggleSet<>();
        for (int[] r : rectangles) {
            for (Point c : corners(r)) {
                corners.toggle(c);
            }
        }
        return corners;
    }

    private List<Point> corners(int[] r) {
        return Arrays.asList(new Point(left(r), bottom(r)),
                new Point(left(r), top(r)),
                new Point(right(r), bottom(r)),
                new Point(right(r), top(r)));
    }

    private static class ToggleSet<T> {
        private final Set<T> elements = new HashSet<>();
        
        void toggle(T element) {
            if (!elements.add(element))
                elements.remove(element);
        }

        int size() {
            return elements.size();
        }

        private boolean containsExactly(Collection<T> corners) {
            return elements.equals(new HashSet<>(corners));
        }
    }
    
    private static class Point {
        private final int x;
        private final int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int hashCode() {
            int hash = 3;
            hash = 31 * hash + this.x;
            hash = 31 * hash + this.y;
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
    }
}
