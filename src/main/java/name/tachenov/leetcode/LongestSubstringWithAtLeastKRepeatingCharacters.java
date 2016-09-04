/*
 * (C) Sergey A. Tachenov
 * This thing belongs to public domain. Really.
 */
package name.tachenov.leetcode;

import java.util.*;

public class LongestSubstringWithAtLeastKRepeatingCharacters {
    public int longestSubstring(String s, int k) {
        if (k > s.length())
            return 0;
        FreqMap fm = new FreqMap(k);
        for (int i = 0; i < s.length(); ++i)
            fm.add(s.charAt(i));
        if (fm.qualifies())
            return s.length();
        int begin = 0, end = s.length();
        for (int len = s.length() - 1; len > 0; --len) {
            if (begin == 0) {
                fm.remove(s.charAt(--end));
            } else {
                fm.remove(s.charAt(begin++));
            }
            if (fm.qualifies())
                return len;
            if (begin == 0) {
                while (end < s.length()) {
                    fm.remove(s.charAt(begin++));
                    fm.add(s.charAt(end++));
                    if (fm.qualifies())
                        return len;
                }
            } else {
                while (begin > 0) {
                    fm.add(s.charAt(--begin));
                    fm.remove(s.charAt(--end));
                    if (fm.qualifies())
                        return len;
                }
            }
        }
        return 0;
    }
    
    private static class FreqMap {
        private final Map<Character, Integer> freq = new HashMap<>();
        private final Set<Character> bad = new HashSet<>();
        private final int k;

        FreqMap(int k) {
            this.k = k;
        }
        
        void add(char c) {
            int count = freq.getOrDefault(c, 0) + 1;
            freq.put(c, count);
            updateBad(count, c);
        }

        private void updateBad(int count, char c) {
            if (count > 0 && count < k)
                bad.add(c);
            else
                bad.remove(c);
        }
        
        void remove(char c) {
            int count = freq.getOrDefault(c, 0) - 1;
            if (count > 0)
                freq.put(c, count);
            else
                freq.remove(c);
            updateBad(count, c);
        }
        
        boolean qualifies() {
            return bad.isEmpty();
        }
    }
}
