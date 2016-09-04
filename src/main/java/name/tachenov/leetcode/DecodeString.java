/*
 * (C) Sergey A. Tachenov
 * This thing belongs to public domain. Really.
 */
package name.tachenov.leetcode;

public class DecodeString {
    public String decodeString(String s) {
        if (s == null)
            return null;
        StringBuilder sb = new StringBuilder();
        decode(s, 0, sb);
        return sb.toString();
    }

    private static int decode(String s, int pos, StringBuilder sb) {
        for (int i = pos; i < s.length(); ++i) {
            char c = s.charAt(i);
            if (c == ']')
                return i;
            if (Character.isDigit(c)) {
                int sq = s.indexOf('[', i);
                int repeat = Integer.parseInt(s.substring(i, sq));
                i = decode(s, sq + 1, repeat, sb);
            } else {
                sb.append(c);
            }
        }
        return s.length();
    }

    private static int decode(String s, int pos, int repeat, StringBuilder sb) {
        int start = sb.length();
        int skipTo = decode(s, pos, sb);
        int end = sb.length();
        for (int i = 1; i < repeat; ++i) {
            sb.append(sb, start, end);
        }
        return skipTo;
    }
}
