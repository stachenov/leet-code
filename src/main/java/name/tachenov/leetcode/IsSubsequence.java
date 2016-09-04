/*
 * (C) Sergey A. Tachenov
 * This thing belongs to public domain. Really.
 */
package name.tachenov.leetcode;

public class IsSubsequence {
    public boolean isSubsequence(String s, String t) {
        if (s == null || t == null)
            return false;
        int tPos = 0;
        for (int sPos = 0; sPos < s.length(); ++sPos) {
            char sc = s.charAt(sPos);
            int scPosInT = t.indexOf(sc, tPos);
            if (scPosInT == -1)
                return false;
            tPos = scPosInT + 1;
        }
        return true;
    }
}
