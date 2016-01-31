/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package name.tachenov.leetcode;

import java.util.Arrays;

/**
 *
 * @author alqualos
 */
public class KMPSearch {
    
    public static void main(String[] args) {
        String s = "ababb";
        int[] kmp = new int[s.length()];
        kmp[0] = -1;
        kmp[1] = 0;
        for (int i = 1, cnd = 0; i < s.length() - 1; ) {
            char c = s.charAt(i);
            if (c == s.charAt(cnd)) {
                kmp[++i] = ++cnd;
            } else if (cnd > 0) {
                cnd = kmp[cnd];
            } else {
                kmp[++i] = 0;
            }
        }
        System.out.println(Arrays.toString(kmp));
        String t = "aabbababb";
        for (int i = 0, j = 0; i < t.length(); ) {
            if (t.charAt(i) == s.charAt(j)) {
                ++i;
                if (++j == s.length()) {
                    System.out.println("Found at " + (i - j));
                    break;
                }
            } else if (kmp[j] >= 0) {
                j = kmp[j];
            } else {
                ++i;
            }
        }
    }
    
}
