/*
 * (C) Sergey A. Tachenov
 * This thing belongs to public domain. Really.
 */
package name.tachenov.leetcode;

import java.util.*;
import java.util.stream.*;

public class PerfectRectangle {
    public boolean isRectangleCover(int[][] rectangles) {
        int totalArea = Stream.of(rectangles).mapToInt(r -> area(r)).sum();
        if (totalArea != area(getBounds(rectangles)))
            return false;
        List<KeyPoint> keyPointsX = new ArrayList<>();
        for (int i = 0; i < rectangles.length; ++i) {
            keyPointsX.add(new KeyPoint(rectangles[i][0], rectangles[i], true));
            keyPointsX.add(new KeyPoint(rectangles[i][2], rectangles[i], true));
        }
        Collections.sort(keyPointsX);
        KeyPointList keyPointsY = new KeyPointList();
        for (KeyPoint kpx : keyPointsX) {
            KeyPoint start = new KeyPoint(kpx.rectangle[1], kpx.rectangle, false);
            KeyPoint end = new KeyPoint(kpx.rectangle[3], kpx.rectangle, false);
            if (kpx.isRectangleEndsHere()) {
                keyPointsY.remove(start);
                keyPointsY.remove(end);
            } else {
                int insertionPoint = keyPointsY.getNonOverlappingInsertionPoint(start, end);
                if (insertionPoint == -1)
                    return false;
                keyPointsY.add(insertionPoint, start, end);
            }
        }
        return true;
    }

    private int[] getBounds(int[][] rectangles) {
        int[] bounds = new int[4];
        int left = Integer.MAX_VALUE, right = Integer.MIN_VALUE,
                top = Integer.MIN_VALUE, bottom = Integer.MAX_VALUE;
        for (int i = 0; i < rectangles.length; ++i) {
            left = Math.min(left, rectangles[i][0]);
            right = Math.max(right, rectangles[i][2]);
            top = Math.max(top, rectangles[i][3]);
            bottom = Math.min(bottom, rectangles[i][1]);
        }
        bounds[0] = left;
        bounds[1] = bottom;
        bounds[2] = right;
        bounds[3] = top;
        return bounds;
    }
    
    private static int area(int[] rectangle) {
        return (rectangle[2] - rectangle[0]) * (rectangle[3] - rectangle[1]);
    }
    
    private static class KeyPointList {
        final List<KeyPoint> keyPoints = new ArrayList<>();

        void remove(KeyPoint keyPoint) {
            keyPoints.remove(Collections.binarySearch(keyPoints, keyPoint));
        }

        int getNonOverlappingInsertionPoint(KeyPoint start, KeyPoint end) {
            int startIndex = -Collections.binarySearch(keyPoints, start) - 1;
            int endIndex = -Collections.binarySearch(keyPoints, end) - 1;
            if (overlaps(startIndex, endIndex))
                return -1;
            return startIndex;
        }

        private boolean overlaps(int startIndex, int endIndex) {
            if (startIndex != endIndex)
                return true; // some rectangle start or ends inside this one
            if (startIndex == 0)
                return false; // this one comes before the first starts
            KeyPoint preceding = keyPoints.get(startIndex - 1);
            if (preceding.isRectangleStartsHere())
                return true; // this one is completely inside another rectangle
            return false;
        }

        void add(int insertionPoint, KeyPoint start, KeyPoint end) {
            keyPoints.add(insertionPoint, start);
            keyPoints.add(insertionPoint + 1, end);
        }
    }
    
    private static class KeyPoint implements Comparable<KeyPoint> {
        final int coordinate;
        final int[] rectangle;
        private final boolean horizontal;

        KeyPoint(int coordinate, int[] rectangle, boolean horizontal) {
            this.coordinate = coordinate;
            this.rectangle = rectangle;
            this.horizontal = horizontal;
        }

        boolean isRectangleStartsHere() {
            return horizontal
                    ? coordinate == rectangle[0]
                    : coordinate == rectangle[1];
        }
        
        boolean isRectangleEndsHere() {
            return horizontal
                    ? coordinate == rectangle[2]
                    : coordinate == rectangle[3];
        }

        @Override
        public int compareTo(KeyPoint o) {
            int xc = Integer.compare(coordinate, o.coordinate);
            if (xc == 0) {
                if (isRectangleEndsHere() && o.isRectangleStartsHere())
                    return -1;
                else if (isRectangleStartsHere() && o.isRectangleEndsHere())
                    return +1;
                else
                    return xc;
            } else {
                return xc;
            }
        }
    }
}
