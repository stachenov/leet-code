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
public class KMT {
    public String shortestPalindrome(String s) {
        final int len = s.length();
        if (len <= 1)
            return s;
        int[] kmp = new int[len];
        kmp[0] = -1; kmp[1] = 0;
        for (int cnd = 0, pos = 2; pos < kmp.length; ) {
            //System.out.println("pos " + pos + " cnd " + cnd);
            if (s.charAt(cnd) == s.charAt(pos - 1)) {
                kmp[pos++] = ++cnd;
                //System.out.println("kmp[" + (pos - 1) + "]=" + kmp[pos - 1]);
            } else if (cnd > 0) {
                cnd = kmp[cnd]; // backtrack
            } else {
                kmp[pos++] = 0;
                //System.out.println("kmp[" + (pos - 1) + "]=" + kmp[pos - 1]);
            }
        }
        String r = new StringBuilder(s).reverse().toString();
        int j = 0; // index in s to compare with reversed[i + j]
        for (int i = 0; i + j < len; ) {
            if (r.charAt(i + j) == s.charAt(j)) {
                ++j;
            } else if (kmp[j] >= 0) {
                i = i + j - kmp[j];
                j = kmp[j];
            } else {
                j = 0;
                ++i;
            }
        }
        // now j is equal to the length of the matched last part, for example:
        //     01234567890
        // r = ababbabbbab (len = 11)
        // s =     babbbabbaba
        //         01234567 <- j == 7
        // 
        return r.substring(0, len - j) + s;
    }
    
    public static void main(String[] args) {
        KMT km = new KMT();
        System.out.println(km.shortestPalindrome("babbbabbaba"));
        System.out.println(km.shortestPalindrome("abbabaab"));
        System.out.println(km.shortestPalindrome("aacecaaa"));
           //System.out.println(km.shortestPalindrome("aaacecaa"));
//        String w = "aba";
//        int[] kmp = new int[w.length() + 1];
//        kmp[0] = -1;
//        kmp[1] = 0;
//        int cnd = 0;
//        for (int pos = 2; pos <= w.length(); ) {
//            System.out.println("pos = " + pos + " cnd = " + cnd);
//            if (w.charAt(pos - 1) == w.charAt(cnd)) {
//                kmp[pos++] = ++cnd;
//            } else if (cnd > 0) {
//                cnd = kmp[cnd];
//            } else {
//                kmp[pos++] = 0;
//            }
//        }
//        for (int i = 0; i < kmp.length; ++i) {
//            //System.out.println(w.charAt(i) + " " + kmp[i]);
//            System.out.println(kmp[i]);
//        }
    }
    
}
