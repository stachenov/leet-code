/*
 * (C) Sergey A. Tachenov
 * This thing belongs to public domain. Really.
 */
package name.tachenov.leetcode;

import java.util.*;

public class LongestAbsoluteFilePath {

    public int lengthLongestPath(String input) {
        Deque<String> currentDir = new ArrayDeque<>();
        int len = 0;
        int longest = 0;
        String[] lines = input.split("\n");
        for (String line : lines) {
            int depth = 1;
            for (char c : line.toCharArray()) {
                if (c != '\t')
                    break;
                ++depth;
            }
            while (depth <= currentDir.size()) {
                len -= currentDir.pop().length();
            }
            boolean dir = !line.contains(".");
            if (dir) {
                String dname = line.substring(depth - 1);
                currentDir.push(dname);
                len += dname.length();
            } else {
                longest = Math.max(longest, len + currentDir.size() + line.length() - (depth - 1));
            }
        }
        return longest;
    }
}
