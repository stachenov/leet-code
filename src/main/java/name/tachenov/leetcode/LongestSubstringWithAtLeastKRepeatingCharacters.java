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
        BadCharsTrackingMap map = new BadCharsTrackingMap(s, k);
        SlidingWindow sw = new SlidingWindow(s, map, k);
        return sw.findLongestString();
    }
    
    private static class BadCharsTrackingMap {
        private final Map<Character, Integer> counts = new HashMap<>();
        private final Set<Character> badChars = new HashSet<>();
        private final int k;

        BadCharsTrackingMap(String s, int k) {
            this.k = k;
            for (int i = 0; i < s.length(); ++i)
                add(s.charAt(i));
        }
        
        private void add(char c) {
            int count = counts.getOrDefault(c, 0) + 1;
            counts.put(c, count);
            updateBadStatus(c, count);
        }

        private void updateBadStatus(char c, int count) {
            if (isBad(count))
                badChars.add(c);
            else
                badChars.remove(c);
        }

        private boolean isBad(int count) {
            return count > 0 && count < k;
        }
        
        void remove(char c) {
            int count = counts.getOrDefault(c, 0) - 1;
            if (count > 0)
                counts.put(c, count);
            else
                counts.remove(c);
            updateBadStatus(c, count);
        }
        
        boolean allGood() {
            return badChars.isEmpty();
        }
    }

    private static class SlidingWindow {

        private final String string;
        private final BadCharsTrackingMap map;
        private int begin;
        private int end;
        private boolean leftToRight;
        private final int k;

        SlidingWindow(String string, BadCharsTrackingMap map, int k) {
            this.string = string;
            this.map = map;
            this.k = k;
            begin = 0;
            end = string.length();
        }

        private int findLongestString() {
            if (map.allGood())
                return string.length();
            for (int len = string.length() - 1; len >= k; --len) {
                setDirection();
                decreaseWindowSize();
                if (map.allGood())
                    return len;
                while (!reachedOtherEnd()) {
                    move();
                    if (map.allGood())
                        return len;
                }
            }
            return 0;
        }

        private void setDirection() {
            leftToRight = begin == 0;
        }

        private void decreaseWindowSize() {
            if (leftToRight)
                removeLastCharacter();
            else
                removeFirstCharacter();
        }

        private void removeLastCharacter() {
            map.remove(string.charAt(--end));
        }

        private void removeFirstCharacter() {
            map.remove(string.charAt(begin++));
        }

        private boolean reachedOtherEnd() {
            if (leftToRight)
                return end == string.length();
            else
                return begin == 0;
        }

        private void move() {
            if (leftToRight)
                moveRight();
            else
                moveLeft();
        }

        private void moveLeft() {
            addFirstCharacter();
            removeLastCharacter();
        }

        private void moveRight() {
            removeFirstCharacter();
            addLastCharacter();
        }

        private void addFirstCharacter() {
            map.add(string.charAt(--begin));
        }

        private void addLastCharacter() {
            map.add(string.charAt(end++));
        }
    }
}
