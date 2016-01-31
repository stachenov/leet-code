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
public class AlienDictionary {
    public String alienOrder(String[] words) {
        if (words.length <= 1)
            return "";
        String prev = words[0];
        int[][] comp = new int[26][26];
        boolean[] seen = new boolean[26];
        for (int i = 1; i < words.length; ++i) {
            String word = words[i];
            char c1 = 0, c2 = 0;
            int len = Math.min(prev.length(), word.length());
            for (int j = 0; j < len; ++j) {
                c1 = prev.charAt(j);
                c2 = word.charAt(j);
                if (c1 != c2)
                    break;
            }
            if (c1 == 0) {
                // one is prefix of the other
                if (prev.length() > word.length())
                    return ""; // not lexicographic
            } else {
                if (comp[c1 - 'a'][c2 - 'a'] == +1)
                    return ""; // contradiction
                if (comp[c1 - 'a'][c2 - 'a'] == 0) {
                    // new pair
                    comp[c1 - 'a'][c2 - 'a'] = -1;
                    comp[c2 - 'a'][c1 - 'a'] = +1;
                    seen[c1 - 'a'] = seen[c2 - 'a'] = true;
                    // process transitive relationships
                    for (int j = 0; j < 26; ++j) {
                        if (comp[c2 - 'a'][j] == -1) {
                            if (comp[c1 - 'a'][j] == +1)
                                return ""; // contradiction
                            if (comp[c1 - 'a'][j] == 0) {
                                // new transitive relationship
                                comp[c1 - 'a'][j] = -1;
                                comp[j][c1 - 'a'] = +1;
                            }
                        }
                    }
                }
            }
            prev = word;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < seen.length; ++i) {
            if (seen[i])
                sb.append((char) ('a' + i));
        }
        char[] order = new char[sb.length()];
        sb.getChars(0, sb.length(), order, 0);
        Character[] boxed = new Character[order.length];
        for (int i = 0; i < boxed.length; ++i) {
            boxed[i] = order[i];
        }
        Arrays.sort(boxed, (c1, c2) -> comp[c1 - 'a'][c2 - 'a']);
        for (int i = 0; i < boxed.length; ++i) {
            order[i] = boxed[i];
        }
        return new String(order);
    }
}
