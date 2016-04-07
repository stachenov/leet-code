/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package name.tachenov.leetcode;

import java.util.Random;
import java.util.regex.Pattern;

/**
 *
 * @author alqualos
 */
public class RegexDP {
    public boolean isMatch(String s, String p) {
        StringBuilder parsedBuilder = new StringBuilder();
        StringBuilder quantifierBuilder = new StringBuilder();
        for (int i = 0; i < p.length(); ++i) {
            char c = p.charAt(i);
            parsedBuilder.append(c);
            if (i < p.length() - 1 && p.charAt(i + 1) == '*') {
                quantifierBuilder.append('*');
                ++i;
            } else {
                quantifierBuilder.append(' ');
            }
        }
        char[] pc = new char[parsedBuilder.length()];
        parsedBuilder.getChars(0, pc.length, pc, 0);
        char[] pq = new char[quantifierBuilder.length()];
        quantifierBuilder.getChars(0, pq.length, pq, 0);
        assert pc.length == pq.length;
        boolean[] matches = new boolean[pc.length + 1];
        matches[0] = true; // an empty pattern always matches an empty string
        for (int i = 0; i < pq.length; ++i) {
            if (pq[i] == '*') {
                matches[i + 1] = true; // still matches an empty string
            } else {
                break;
            }
        }
        // now matches[i] is whether the subpattern of length i matches an empty string
        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            boolean matchedShorter = matches[0];
            matches[0] = false; // an empty pattern matches only an empty string
            for (int j = 0; j < pc.length; ++j) {
                boolean old = matches[j + 1];
                if (pq[j] == '*') {
                    matches[j + 1] = matches[j] ||
                            matches[j + 1] && (pc[j] == '.' || pc[j] == c) ||
                            matchedShorter && (pc[j] == '.' || pc[j] == c);
                } else {
                    matches[j + 1] = matchedShorter && (pc[j] == '.' || pc[j] == c);
                }
                matchedShorter = old;
            }
        }
        return matches[matches.length - 1];
    }

    public static void main(String[] args) {
        RegexDP dp = new RegexDP();
        char[] s = new char[30];
        Random rnd = new Random(0);
        Pattern re = Pattern.compile("a*b*c*abc.*a*b*");
        dp.isMatch("aabc", "a*b*c*abc.*a*b*");
        for (int i = 0; i < 10000000; ++i) {
            for (int j = 0; j < s.length; ++j) {
                s[j] = (char) (rnd.nextInt(3) + 'a');
            }
            boolean dpMatched = dp.isMatch(new String(s), "a*b*c*abc.*a*b*");
            boolean javaMatched = re.matcher(new String(s)).matches();
            if (dpMatched != javaMatched) {
                System.out.println(new String(s));
            }
        }
    }
    
}
