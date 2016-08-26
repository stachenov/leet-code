/*
 * (C) Sergey A. Tachenov
 * This thing belongs to public domain. Really.
 */
package name.tachenov.leetcode;

import java.util.*;

public class LexicographicalNumbers {
    private int limit;
    private List<Integer> result;

    public List<Integer> lexicalOrder(int n) {
        if (n <= 0)
            return Arrays.asList();
        limit = n;
        result = new ArrayList<>();
        for (int i = 1; i <= 9; ++i)
            addStartingWith(i);
        return result;
    }

    private void addStartingWith(int leftPart) {
        if (leftPart > limit)
            return;
        result.add(leftPart);
        for (int i = 0; i <= 9; ++i)
            addStartingWith(leftPart * 10 + i);
    }
}
