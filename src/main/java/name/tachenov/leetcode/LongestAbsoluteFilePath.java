/*
 * (C) Sergey A. Tachenov
 * This thing belongs to public domain. Really.
 */
package name.tachenov.leetcode;

import java.util.*;

public class LongestAbsoluteFilePath {

    public int lengthLongestPath(String input) {
        Path currentDir = new Path();
        int longest = 0;
        for (String line : input.split("\n")) {
            int tabsCount = countLeadingTabs(line);
            currentDir.cdUpToDepth(tabsCount);
            String localName = line.substring(tabsCount);
            boolean dir = !localName.contains(".");
            if (dir)
                currentDir.cd(localName);
            else
                longest = Math.max(longest, currentDir.length + localName.length());
        }
        return longest;
    }

    private int countLeadingTabs(String line) {
        for (int i = 0; i < line.length(); ++i) {
            if (line.charAt(i) != '\t')
                return i;
        }
        return line.length();
    }
    
    private static class Path {
        private static final int SEPARATOR_LENGTH = 1;
        private final Deque<String> stack = new ArrayDeque<>();
        private int length = 0;
        
        void cd(String dir) {
            stack.push(dir);
            addLength(dir);
        }

        private void addLength(String dir) {
            length += dir.length() + SEPARATOR_LENGTH;
        }

        private void cdUpToDepth(int target) {
            while (depth() > target)
                cdUp();
        }

        private int depth() {
            return stack.size();
        }

        private void cdUp() {
            String dirName = stack.pop();
            subtractLength(dirName);
        }

        private void subtractLength(String dirName) {
            length -= dirName.length() + SEPARATOR_LENGTH;
        }
    }
}
