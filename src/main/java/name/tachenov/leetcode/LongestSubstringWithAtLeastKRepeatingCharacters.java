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
        MapTrackingInvalidChars map = computeMapWithInvalidChars(k, s);
        if (map.allValid())
            return s.length();
        String[] candidates = s.split(map.getInvalidCharsPattern());
        int max = 0;
        for (String candidate : candidates) {
            assert candidate.length() < s.length();
            max = Math.max(max, longestSubstring(candidate, k));
        }
        return max;
    }

    private MapTrackingInvalidChars computeMapWithInvalidChars(int k, String s) {
        MapTrackingInvalidChars map = new MapTrackingInvalidChars(k);
        for (char c : s.toCharArray())
            map.add(c);
        return map;
    }
    
    private static class MapTrackingInvalidChars {
        private final int k;
        private final Map<Character, Integer> counts = new HashMap<>();
        private final Set<Character> invalidChars = new HashSet<>();

        MapTrackingInvalidChars(int k) {
            this.k = k;
        }
        
        private void add(char c) {
            int count = counts.getOrDefault(c, 0) + 1;
            counts.put(c, count);
            updateValidity(c, count);
        }

        private void updateValidity(char c, int count) {
            if (isInvalidCount(count))
                invalidChars.add(c);
            else
                invalidChars.remove(c);
        }

        private boolean isInvalidCount(int count) {
            return count > 0 && count < k;
        }
        
        boolean allValid() {
            return invalidChars.isEmpty();
        }

        private String getInvalidCharsPattern() {
            StringBuilder invalids = new StringBuilder();
            invalids.append('[');
            for (char c : invalidChars)
                invalids.append(c);
            invalids.append("]+");
            return invalids.toString();
        }
    }
}
