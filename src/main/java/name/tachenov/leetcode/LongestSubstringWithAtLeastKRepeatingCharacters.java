/*
 * (C) Sergey A. Tachenov
 * This thing belongs to public domain. Really.
 */
package name.tachenov.leetcode;

import java.util.*;

public class LongestSubstringWithAtLeastKRepeatingCharacters {
    public int longestSubstring(String s, int k) {
        return longestSubstring(s, k, 0, s.length());
    }

    private int longestSubstring(String s, int k, int start, int end) {
        int len = end - start;
        if (k > len)
            return 0;
        MapTrackingInvalidChars map = new MapTrackingInvalidChars(s, k, start, end);
        if (map.allValid())
            return len;
        List<Interval> intervals = map.findValidIntervals();
        int max = 0;
        for (Interval interval : intervals) {
            assert interval.start > start || interval.end < end;
            max = Math.max(max, longestSubstring(s, k, interval.start, interval.end));
        }
        return max;
    }
    
    private static class MapTrackingInvalidChars {
        private final String s;
        private final int k;
        private final int start;
        private final int end;
        private final Map<Character, Integer> counts = new HashMap<>();
        private final Set<Character> invalidChars = new HashSet<>();

        MapTrackingInvalidChars(String s, int k, int start, int end) {
            this.s = s;
            this.k = k;
            this.start = start;
            this.end = end;
            for (int i = start; i < end; ++i)
                add(s.charAt(i));
        }
        
        private void add(char c) {
            int count = counts.getOrDefault(c, 0) + 1;
            counts.put(c, count);
            updateValidity(c, count);
        }

        private void updateValidity(char c, int count) {
            if (isValidCount(count))
                invalidChars.add(c);
            else
                invalidChars.remove(c);
        }

        private boolean isValidCount(int count) {
            return count > 0 && count < k;
        }
        
        void remove(char c) {
            int count = counts.getOrDefault(c, 0) - 1;
            if (count > 0)
                counts.put(c, count);
            else
                counts.remove(c);
            updateValidity(c, count);
        }
        
        boolean allValid() {
            return invalidChars.isEmpty();
        }

        private List<Interval> findValidIntervals() {
            List<Interval> intervals = new ArrayList<>();
            for (int i = start; i < end; ) {
                while (i < end && isInvalidChar(s.charAt(i)))
                    ++i;
                int j = i + 1;
                while (j < end && isValidChar(s.charAt(j)))
                    ++j;
                if (i < end)
                    intervals.add(new Interval(i, j));
                i = j + 1;
            }
            return intervals;
        }

        private boolean isInvalidChar(char c) {
            return invalidChars.contains(c);
        }

        private boolean isValidChar(char c) {
            return !isInvalidChar(c);
        }
    }
    
    private static class Interval {
        final int start;
        final int end;

        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}
