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
public class FindTheDifference {
    public char findTheDifference(String s, String t) {
        Map<Character, Integer> freqS = new HashMap<>();
        for (char c : s.toCharArray())
            freqS.put(c, freqS.getOrDefault(c, 0) + 1);
        Map<Character, Integer> freqT = new HashMap<>();
        for (char c : t.toCharArray()) {
            int freq = freqT.getOrDefault(c, 0) + 1;
            if (freq > freqS.getOrDefault(c, 0))
                return c;
            freqT.put(c, freqT.getOrDefault(c, 0) + 1);
        }
        return '\0';
    }
}
