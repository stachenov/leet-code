/*
 * (C) Sergey A. Tachenov
 * This thing belongs to public domain. Really.
 */
package name.tachenov.leetcode;

public class WaterAndJugProblem {

    public boolean canMeasureWater(int x, int y, int z) {
        if (z == 0)
            return true;
        if (z > x + y)
            return false;
        return z % gcd(x, y) == 0;
    }

    private static int gcd(int x, int y) {
        return y == 0 ? x : gcd(y, x % y);
    }
    
}
