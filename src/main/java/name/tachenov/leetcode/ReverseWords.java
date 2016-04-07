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
public class ReverseWords {
    public void reverseWords(char[] s) {
        for (int i = 0, j = s.length - 1; i < j; ++i, --j) {
            char c = s[i];
            s[i] = s[j];
            s[j] = c;
        }
        for (int i = 0, j = 0; i < s.length; ) {
            while (j < s.length && s[j] != ' ') {
                ++j;
            }
            int end = j--;
            for ( ; i < j; ++i, --j) {
                char c = s[i];
                s[i] = s[j];
                s[j] = c;
            }
            i = j = end + 1;
        }
    }
    
    public static void main(String[] args) {
        ReverseWords rw = new ReverseWords();
        char[] s = "a bus".toCharArray();
        rw.reverseWords(s);
        System.out.println(new String(s));
    }
}
