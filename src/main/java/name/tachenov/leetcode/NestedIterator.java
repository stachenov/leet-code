/*
 * (C) Sergey A. Tachenov
 * This thing belongs to public domain. Really.
 */
package name.tachenov.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;

public class NestedIterator implements Iterator<Integer> {
    // This is the interface that allows for creating nested lists.
    // You should not implement it, or speculate about its implementation
    public interface NestedInteger {

        // @return true if this NestedInteger holds a single integer, rather than a nested list.
        public boolean isInteger();

        // @return the single integer that this NestedInteger holds, if it holds a single integer
        // Return null if this NestedInteger holds a nested list
        public Integer getInteger();

        // @return the nested list that this NestedInteger holds, if it holds a nested list
        // Return null if this NestedInteger holds a single integer
        public List<NestedInteger> getList();
    }
    
    private final Deque<Iterator<NestedInteger>> stack = new ArrayDeque<>();
    private Integer next = null;

    public NestedIterator(List<NestedInteger> nestedList) {
        stack.push(nestedList.iterator());
        goToNext();
    }

    @Override
    public Integer next() {
        Integer n = this.next;
        goToNext();
        return n;
    }

    @Override
    public boolean hasNext() {
        return next != null;
    }
    
    private void goToNext() {
        for (boolean needToGoDeeper = true; needToGoDeeper; ) {
            needToGoDeeper = false;
            while (!stack.isEmpty() && !stack.peek().hasNext()) {
                stack.pop();
            }
            if (stack.isEmpty()) {
                next = null;
            } else {
                NestedInteger nextList = stack.peek().next();
                if (nextList.isInteger()) {
                    next = nextList.getInteger();
                } else {
                    stack.push(nextList.getList().iterator());
                    needToGoDeeper = true;
                }
            }
        }
    }
}
