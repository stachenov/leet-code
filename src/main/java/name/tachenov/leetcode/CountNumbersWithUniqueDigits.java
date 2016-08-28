/*
 * (C) Sergey A. Tachenov
 * This thing belongs to public domain. Really.
 */
package name.tachenov.leetcode;

public class CountNumbersWithUniqueDigits {

    public int countNumbersWithUniqueDigits(int n) {
        if (n > 10)
            n = 10;
        int count = 0;
        for (int i = n; i > 0; --i) {
            count += countForFixedNumberOfDigits(i);
        }
        return count;
    }
    
    private static int countForFixedNumberOfDigits(int n) {
        if (n == 1) {
            return 10;
        } else {
            return 9 * factorial(n - 1) * combinations(n - 1, 9);
        }
    }

    private static int factorial(int n) {
        return n == 0 ? 1 : n * factorial(n - 1);
    }

    private static int combinations(int k, int n) {
        return factorial(n) / (factorial(k) * factorial(n - k));
    }
}
