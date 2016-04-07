/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package name.tachenov.leetcode;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author alqualos
 */
public class PeekingIterator implements Iterator<Integer> {

    private final Iterator<Integer> iterator;
    private Integer next = null;

    public PeekingIterator(Iterator<Integer> iterator) {
        // initialize any member here.
        this.iterator = iterator;
    }

    // Returns the next element in the iteration without advancing the iterator.
    public Integer peek() {
        if (next == null) {
            next = iterator.next();
        }
        return next;
    }

	// hasNext() and next() should behave the same as in the Iterator interface.
    // Override them if needed.
    @Override
    public Integer next() {
        if (next == null) {
            return iterator.next();
        } else {
            Integer n = next;
            next = null;
            return n;
        }
    }

    @Override
    public boolean hasNext() {
        return next != null || iterator.hasNext();
    }
    
    public static void main(String[] args) {
        List<Integer> laj = Arrays.asList(1, 2, 3);
        PeekingIterator pi;
        pi = new PeekingIterator(laj.iterator());
        System.out.println(pi.next());
        System.out.println(pi.next());
        System.out.println(pi.peek());
        System.out.println(pi.next());
        System.out.println(pi.hasNext());
    }
}
