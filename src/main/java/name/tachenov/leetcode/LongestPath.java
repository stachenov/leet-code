/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package name.tachenov.leetcode;

import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 *
 * @author alqualos
 */
public class LongestPath {
    
    private String longestPath(String fs) {
        StringTokenizer st = new StringTokenizer(fs, "\n\t", true);
        int depth = 0, newDepth = 1;
        String name = null, longest = null;
        int maxLength = -1;
        PathStack stack = new PathStack();
        while (st.hasMoreTokens()) {
            String token = st.nextToken();
            switch (token) {
                case "\t":
                    ++newDepth;
                    break;
                case "\n":
                    if (name == null)
                        continue;
                    for (int d = depth; d >= newDepth; --d) {
                        stack.pop();
                    }
                    stack.push(name);
                    depth = newDepth;
                    newDepth = 1;
                    int lastDot = name.lastIndexOf('.');
                    if (lastDot > 0 && lastDot < name.length() - 1) {
                        if (stack.length > maxLength) {
                            longest = stack.getFullName();
                            maxLength = longest.length();
                        }
                    }
                    name = null;
                    break;
                default:
                    name = token;
                    break;
            }
        }
        return longest;
    }
    
    private static class PathStack {
        private final Deque<String> elements = new LinkedList<>();
        private int length = 0;
        
        public void push(String element) {
            elements.push(element);
            length += 1 + element.length(); // '/' + name
        }
        
        public void pop() {
            String removed = elements.pop();
            length -= 1 + removed.length();
        }
        
        public String getFullName() {
            StringBuilder sb = new StringBuilder();
            for (Iterator<String> it = elements.descendingIterator();
                    it.hasNext(); ) {
                sb.append('/');
                sb.append(it.next());
            }
            return sb.toString();
        }

    }
    
    public static void main(String[] args) {
        String fs = "dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\t\t\tVeryLongFileName.laj\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext";
        if (!fs.endsWith("\n")) {
            fs += "\n";
        }
        System.out.println(fs);
        System.out.println("Longest:");
        System.out.println(new LongestPath().longestPath(fs));
    }
    
}
