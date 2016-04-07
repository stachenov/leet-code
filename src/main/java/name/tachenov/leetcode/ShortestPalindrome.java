/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package name.tachenov.leetcode;

/**
 *
 * @author alqualos
 */
public class ShortestPalindrome {
    public String shortestPalindrome(String s) {
        StringBuilder added = new StringBuilder();
        int len = s.length();
        int lastIndex = len - 1;
        RollingHash hashAdded = new RollingHash();
        RollingHash hashLeft = new RollingHash(s, 0, len / 2);
        RollingHash hashRight = new RollingHash(s, len, (len + 1) / 2);
        while (!RollingHash.equal(hashAdded, hashLeft, hashRight)
               || !isPalindrome(added + s)) {
            char newChar = s.charAt(lastIndex--);
            if (len % 2 == 0) {
                // len is to become odd, the right part remains
                added.append(newChar);
                hashAdded.append(newChar);
                ++len;
                int mid = len / 2 - added.length();
                hashLeft.remove(s.charAt(mid)); // mid char
            } else {
                // about to become even
                int mid = len / 2 - added.length();
                hashRight.append(s.charAt(mid)); // mid char
                added.append(newChar);
                hashAdded.append(newChar);
                ++len;
            }
        }
        return added + s;
    }
    
    private static boolean isPalindrome(CharSequence s) {
        for (int i = 0, j = s.length() - 1; i < j; ++i, --j) {
            if (s.charAt(i) != s.charAt(j))
                return false;
        }
        return true;
    }
    
    private static class RollingHash {
        private int sum0 = 0, sum1 = 0, count = 0;

        private RollingHash() {
        }

        private RollingHash(CharSequence s, int start, int end) {
            for (int i = end >= start ? start : start - 1;
                    end >= start ? i < end : i >= end;
                    i = (end >= start) ? i + 1 : i - 1) {
                append(s.charAt(i));
            }
        }
        
        private void append(char c) {
            sum0 += c;
            sum1 += sum0;
            ++count;
        }
        
        private void remove(char c) {
            sum1 -= sum0;
            sum0 -= c;
            --count;
        }
        
        public static boolean equal(RollingHash added, RollingHash left, RollingHash right) {
            return added.sum0 + left.sum0 == right.sum0
                    && added.sum1 + left.sum1 + left.count * added.sum0 == right.sum1;
        }
    }
    
    public static void main(String[] args) {
        ShortestPalindrome p = new ShortestPalindrome();
        System.out.println(p.shortestPalindrome("babbbabbaba"));
    }
}
