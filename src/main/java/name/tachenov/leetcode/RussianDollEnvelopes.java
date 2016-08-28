/*
 * (C) Sergey A. Tachenov
 * This thing belongs to public domain. Really.
 */
package name.tachenov.leetcode;

import java.util.*;

public class RussianDollEnvelopes {

    public int maxEnvelopes(int[][] envelopes) {
        Arrays.sort(envelopes, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                int cmpw = Integer.compare(o1[0], o2[0]);
                if (cmpw == 0)
                    return Integer.compare(o2[1], o1[1]);
                else
                    return cmpw;
            }
        });
        final int n = envelopes.length;
        int[] minHeight = new int[n];
        int len = 0;
        for (int[] env : envelopes) {
            if (len == 0 || env[1] > minHeight[len - 1]) {
                minHeight[len++] = env[1];
            } else {
                int insertAt = Arrays.binarySearch(minHeight, 0, len, env[1]);
                if (insertAt < 0)
                    insertAt = -insertAt - 1;
                minHeight[insertAt] = env[1];
            }
        }
        return len;
    }

}
