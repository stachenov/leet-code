/*
 * (C) Sergey A. Tachenov
 * This thing belongs to public domain. Really.
 */
package name.tachenov.leetcode;

public class DivideTwoIntegers {
    public int divide(int dividend, int divisor) {
        if (divisor == 0 || (dividend == Integer.MIN_VALUE && divisor == -1))
            return Integer.MAX_VALUE;
        if (divisor == Integer.MIN_VALUE)
            return dividend == Integer.MIN_VALUE ? 1 : 0;
        if (divisor == 1) // needed for Integer.MIN_VALUE / 1
            return dividend;
        int remainder = Math.abs(dividend); // think of MIN_VALUE as MAX_VALUE + 1
        int shiftedDivisor = Math.abs(divisor);
        int shift = 0;
        while (shiftedDivisor > 0 && remainder - shiftedDivisor >= 0) {
            shiftedDivisor <<= 1;
            ++shift;
        }
        int quotient;
        if (dividend == Integer.MIN_VALUE && shiftedDivisor == Integer.MIN_VALUE) {
            quotient = 1 << shift;
        } else {
            quotient = 0;
            while (shift > 0) {
                --shift;
                shiftedDivisor >>>= 1;
                if (remainder - shiftedDivisor >= 0) {
                    remainder -= shiftedDivisor;
                    quotient |= 1 << shift;
                }
            }
        }
        if (dividend < 0 && divisor > 0 || dividend > 0 && divisor < 0)
            return -quotient;
        else
            return quotient;
    }
}