/*
 * (C) Sergey A. Tachenov
 * This thing belongs to public domain. Really.
 */
package name.tachenov.leetcode;

import java.util.HashMap;
import java.util.Map;

public class LongestAtMostK {

    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        int result = 0;
        Map<Integer, Integer> freq = new HashMap<>();
        for (int i = 0, j = 0; j < s.length(); ) {
            int prevJ = j;
            while (freq.size() <= k && j < s.length()) {
                int c = s.codePointAt(j);
                freq.put(c, freq.getOrDefault(c, 0) + 1);
                prevJ = j;
                j += Character.charCount(c);
            }
            if (freq.size() <= k) {
                result = Math.max(result, j - i);
            } else {
                result = Math.max(result, prevJ - i);
            }
            while (freq.size() > k) {
                int c = s.codePointAt(i);
                int f = freq.get(c);
                if (f == 1) {
                    freq.remove(c);
                } else {
                    freq.put(c, f - 1);
                }
                i += Character.charCount(c);
                assert i <= j;
            }
        }
        return result;
    }

}
