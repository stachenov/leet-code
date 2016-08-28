/*
 * (C) Sergey A. Tachenov
 * This thing belongs to public domain. Really.
 */
package name.tachenov.leetcode;

import static java.lang.Math.*;
import java.util.*;

public class LineReflection {

    public boolean isReflected(int[][] points) {
        if (points.length == 0)
            return true;
        Set<Point> uniquePoints = getUniquePoints(points);
        ReflectionLine line = findReflectionLine(uniquePoints);
        return line.allPointsHaveReflection(uniquePoints);
    }

    private Set<Point> getUniquePoints(int[][] points) {
        Set<Point> uniquePoints = new HashSet<>();
        for (int[] p : points) {
            Point point = new Point(p[0], p[1]);
            uniquePoints.add(point);
        }
        return uniquePoints;
    }

    private ReflectionLine findReflectionLine(Set<Point> uniquePoints) {
        int minX = Integer.MAX_VALUE, maxX = Integer.MIN_VALUE;
        for (Point p : uniquePoints) {
            minX = min(minX, p.x);
            maxX = max(maxX, p.x);
        }
        return new ReflectionLine(minX, maxX);
    }
    
    private static class ReflectionLine {
        final int x;
        final boolean andAHalf;

        ReflectionLine(int x1, int x2) {
            this.x = (x1 + x2) >>> 1;
            this.andAHalf = ((x1 + x2) & 1) != 0;
        }

        boolean allPointsHaveReflection(Set<Point> uniquePoints) {
            for (Point p : uniquePoints) {
                Point r = reflect(p);
                if (!uniquePoints.contains(r))
                    return false;
            }
            return true;
        }

        private Point reflect(Point p) {
            if (onTheLine(p))
                return p;
            if (andAHalf) {
                if (p.x < x)
                    return new Point(x + (x - p.x) + 1, p.y);
                else
                    return new Point(x - (p.x - x - 1), p.y);
            } else {
                if (p.x < x)
                    return new Point(x + (x - p.x), p.y);
                else
                    return new Point(x - (p.x - x), p.y);
            }
        }

        private boolean onTheLine(Point p) {
            return !andAHalf && p.x == x;
        }
    }
    
    private static class Point {
        final int x;
        final int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int hashCode() {
            int hash = 3;
            hash = 97 * hash + this.x;
            hash = 97 * hash + this.y;
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
