/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package name.tachenov.leetcode;

import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.Deque;
import java.util.Iterator;
import java.util.PriorityQueue;

/**
 *
 * @author alqualos
 */
public class MedianFinder {
    private final PriorityQueue<Integer> left = new PriorityQueue<>(
            new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return Integer.compare(o2, o1);
        }
    });
    private final PriorityQueue<Integer> right = new PriorityQueue<>();

    // Adds a number into the data structure.
    public void addNum(int num) {
        if (left.isEmpty()) {
            left.add(num);
        } else if (left.size() == right.size()) {
            Integer l = left.peek();
            Integer r = right.peek();
            if (num <= r) {
                left.add(num);
            } else {
                left.add(right.remove());
                right.add(num);
            }
        } else {
            Integer mid = left.peek();
            if (num >= mid) {
                right.add(num);
            } else {
                right.add(left.remove());
                left.add(num);
            }
        }
    }

    // Returns the median of current data stream
    public double findMedian() {
        if (left.size() == right.size()) {
            return ((double) left.peek() + right.peek()) / 2.0;
        } else {
            return left.peek();
        }
    }
    
    public static void main(String[] args) {
        MedianFinder mf = new MedianFinder();
        mf.addNum(1);
        System.out.println(mf.findMedian());
        mf.addNum(2);
        System.out.println(mf.findMedian());
        mf.addNum(3);
        System.out.println(mf.findMedian());
    }
};
