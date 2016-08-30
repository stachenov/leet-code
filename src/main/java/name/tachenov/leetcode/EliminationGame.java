/*
 * (C) Sergey A. Tachenov
 * This thing belongs to public domain. Really.
 */
package name.tachenov.leetcode;

public class EliminationGame {
    public int lastRemaining(int n) {
        return 1 + ((Integer.highestOneBit(n) - 1) & (n | 0x55555555));
    }
}
