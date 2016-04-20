package name.tachenov.leetcode;

/*
 * (C) Sergey A. Tachenov
 * This thing belongs to public domain. Really.
 */

/**
 *
 * @author Sergey A. Tachenov
 */
public class IsPowerOf4 {

    public boolean isPowerOfFour(int num) {
        return Integer.bitCount(num) == 1 && Integer.numberOfTrailingZeros(num) % 2 == 0;
    }
    
}
