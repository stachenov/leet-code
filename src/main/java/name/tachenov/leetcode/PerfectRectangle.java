/*
 * (C) Sergey A. Tachenov
 * This thing belongs to public domain. Really.
 */
package name.tachenov.leetcode;

import java.util.*;

public class PerfectRectangle {
    public boolean isRectangleCover(int[][] rectangles) {
        int left = Integer.MAX_VALUE, right = Integer.MIN_VALUE,
                top = Integer.MIN_VALUE, bottom = Integer.MAX_VALUE;
        int totalArea = 0;
        List<KeyPoint> keyPointsX = new ArrayList<>();
        for (int i = 0; i < rectangles.length; ++i) {
            left = Math.min(left, rectangles[i][0]);
            right = Math.max(right, rectangles[i][2]);
            top = Math.max(top, rectangles[i][3]);
            bottom = Math.min(bottom, rectangles[i][1]);
            totalArea += (rectangles[i][2] - rectangles[i][0])
                    * (rectangles[i][3] - rectangles[i][1]);
            keyPointsX.add(new KeyPoint(rectangles[i][0], rectangles[i], true));
            keyPointsX.add(new KeyPoint(rectangles[i][2], rectangles[i], true));
        }
        Collections.sort(keyPointsX);
        List<KeyPoint> keyPointsY = new ArrayList<>();
        for (KeyPoint kpx : keyPointsX) {
            if (kpx.isEnd()) {
                int s = Collections.binarySearch(keyPointsY, new KeyPoint(kpx.rectangle[1], kpx.rectangle, false));
                keyPointsY.remove(s);
                int e = Collections.binarySearch(keyPointsY, new KeyPoint(kpx.rectangle[3], kpx.rectangle, false));
                keyPointsY.remove(e);
            } else {
                KeyPoint ys = new KeyPoint(kpx.rectangle[1], kpx.rectangle, false);
                KeyPoint ye = new KeyPoint(kpx.rectangle[3], kpx.rectangle, false);
                int poss = -Collections.binarySearch(keyPointsY, ys) - 1;
                int pose = -Collections.binarySearch(keyPointsY, ye) - 1;
                if (poss != pose)
                    return false;
                if (poss > 0 && keyPointsY.get(poss - 1).isStart())
                    return false;
                keyPointsY.add(poss, ys);
                keyPointsY.add(poss + 1, ye);
            }
        }
        return totalArea == (top - bottom) * (right - left);
    }
    
    private static class KeyPoint implements Comparable<KeyPoint> {
        int z;
        int[] rectangle;
        private final boolean horizontal;

        KeyPoint(int z, int[] rectangle, boolean horizontal) {
            this.z = z;
            this.rectangle = rectangle;
            this.horizontal = horizontal;
        }
        
        boolean isEnd() {
            return horizontal ? z == rectangle[2] : z == rectangle[3];
        }

        @Override
        public int compareTo(KeyPoint o) {
            int xc = Integer.compare(z, o.z);
            if (xc == 0) {
                if (isEnd() && !o.isEnd())
                    return -1;
                else if (!isEnd() && o.isEnd())
                    return +1;
                else
                    return xc;
            } else {
                return xc;
            }
        }

        @Override
        public int hashCode() {
            int hash = 7;
            hash = 79 * hash + this.z;
            hash = 79 * hash + this.rectangle.hashCode();
            return hash;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final KeyPoint other = (KeyPoint) obj;
            if (this.z != other.z) {
                return false;
            }
            if (this.rectangle != other.rectangle) {
                return false;
            }
            if (this.horizontal != other.horizontal)
                return false;
            return true;
        }

        boolean isStart() {
            return !isEnd();
        }
    }
}
