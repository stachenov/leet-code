/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package name.tachenov.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alqualos
 */
public class PalindromePermutation {
    public List<List<String>> partition(String s) {
        List<List<String>> parts = new ArrayList<>();
        partition(s.toCharArray(), 0, new ArrayList<>(), parts);
        return parts;
    }
    
    private static void partition(char[] s, int pos, List<String> part, List<List<String>> parts) {
        if (pos == s.length) {
            parts.add(new ArrayList<>(part));
            return;
        }
        for (int i = pos + 1, ls1 = 0, ls2 = 0, rs1 = 0, rs2 = 0; i <= s.length; ++i) {
            // ls1, ls2, rs1, rs2 - Fletcher-like checksum of both parts (right one reversed)
            int odd = -((i - pos) & 1), even = ~odd; // bit tricks to avoid branching
            char mid = s[pos + (i - pos - 1) / 2];
            rs2 -= rs1 & even; // remove the mid character from the right part
            rs1 -= mid & even;
            ls1 += mid & odd; // append the mid character to the left part
            ls2 += ls1 & odd;
            char с = s[i - 1];
            rs1 += с; // prepend the right character to the right part
            rs2 += с * ((i - pos + 1) / 2);
            if (ls1 == rs1 && ls2 == rs2 && isPalindrome(s, pos, i)) {
                part.add(new String(s, pos, i - pos));
                partition(s, i, part, parts);
                part.remove(part.size() - 1); // backtrack
            }
        }
    }
    
    private static boolean isPalindrome(char[] s, int start, int end) {
        for (int i = start, j = end - 1; i < j; ++i, --j) {
            if (s[i] != s[j]) { // surrogate pairs BUG!
                return false;
            }
        }
        return true;
    }
    
    public static void main(String[] args) {
        PalindromePermutation pp = new PalindromePermutation();
        System.out.println(pp.partition("aabbaa"));
    }
}
