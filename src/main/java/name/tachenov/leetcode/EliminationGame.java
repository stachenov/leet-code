/*
 * (C) Sergey A. Tachenov
 * This thing belongs to public domain. Really.
 */
package name.tachenov.leetcode;

import java.util.*;

/**
 *
 * @author Sergey A. Tachenov
 */
public class EliminationGame {
    public int lastRemaining(int n) {
        int a = 1, b = 1; // 1k + 1
        int m = n;
        boolean ltr = true;
        while (m > 1) {
            if (ltr || (m & 1) == 1) {
                b += a;
            }
            a *= 2;
            m /= 2;
            ltr = !ltr;
        }
        return b;
    }
}
