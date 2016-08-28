/*
 * (C) Sergey A. Tachenov
 * This thing belongs to public domain. Really.
 */
package name.tachenov.leetcode;

import java.util.*;

public class LineReflection {
    private static final Comparator<Point> X_ASC
            = (p1, p2) -> Integer.compare(p1.x, p2.x);
    private static final Comparator<Point> X_ASC_Y_ASC
            = (p1, p2) -> {
        int cmpx = Integer.compare(p1.x, p2.x);
        if (cmpx == 0)
            return Integer.compare(p1.y, p2.y);
        else
            return cmpx;
    };
    private static final Comparator<Point> X_DESC_Y_ASC
            = (p1, p2) -> {
        int cmpx = Integer.compare(p2.x, p1.x);
        if (cmpx == 0)
            return Integer.compare(p1.y, p2.y);
        else
            return cmpx;
    };

    public boolean isReflected(int[][] points) {
        if (points.length == 0)
            return true;
        List<Point> uniquePoints = getUniquePoints(points);
        Collections.sort(uniquePoints, X_ASC);
        ReflectionLine line = findReflectionLine(uniquePoints);
        List<Point> pointsToTheLeft = pointsToTheLeft(uniquePoints);
        List<Point> pointsToTheRight = pointsToTheRight(uniquePoints);
        assert pointsToTheLeft.size() == pointsToTheRight.size();
        Collections.sort(pointsToTheLeft, X_ASC_Y_ASC);
        Collections.sort(pointsToTheRight, X_DESC_Y_ASC);
        for (int i = 0; i < pointsToTheLeft.size(); ++i) {
            if (!line.reflects(pointsToTheLeft.get(i), pointsToTheRight.get(i)))
                return false;
        }
        return true;
    }

    private List<Point> getUniquePoints(int[][] points) {
        List<Point> uniquePoints = new ArrayList<>();
        Set<Point> added = new HashSet<>();
        for (int[] p : points) {
            Point point = new Point(p[0], p[1]);
            if (added.add(point))
                uniquePoints.add(point);
        }
        return uniquePoints;
    }

    private ReflectionLine findReflectionLine(List<Point> uniquePoints) {
        int mid = uniquePoints.size() / 2;
        if (uniquePoints.size() % 2 == 0) {
            int x1 = uniquePoints.get(mid - 1).x;
            int x2 = uniquePoints.get(mid).x;
            return new ReflectionLine(x1, x2);
        } else {
            return new ReflectionLine(uniquePoints.get(mid).x);
        }
    }

    private List<Point> pointsToTheLeft(List<Point> uniquePoints) {
        return uniquePoints.subList(0, uniquePoints.size() / 2);
    }

    private List<Point> pointsToTheRight(List<Point> uniquePoints) {
        if ((uniquePoints.size() & 1) == 1)
            return uniquePoints.subList(uniquePoints.size() / 2 + 1, uniquePoints.size());
        else
            return uniquePoints.subList(uniquePoints.size() / 2, uniquePoints.size());
    }
    
    private static class ReflectionLine {
        final int x;
        final boolean andAHalf;

        ReflectionLine(int x1, int x2) {
            this.x = (x1 + x2) >>> 1;
            this.andAHalf = ((x1 + x2) & 1) != 0;
        }

        ReflectionLine(int x) {
            this.x = x;
            this.andAHalf = false;
        }

        boolean reflects(Point p1, Point p2) {
            if (bothPointsOnTheLine(p1, p2))
                return true;
            if (p1.y != p2.y)
                return false;
            if (andAHalf)
                return p2.x - x == x - p1.x + 1;
            else
                return p2.x - x == x - p1.x;
        }

        private boolean bothPointsOnTheLine(Point p1, Point p2) {
            return !andAHalf && p1.x == x && p2.x == x;
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
