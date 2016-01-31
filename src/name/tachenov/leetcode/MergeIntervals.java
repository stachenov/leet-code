/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package name.tachenov.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author alqualos
 */
public class MergeIntervals {
    public List<Interval> merge(List<Interval> intervals) {
        Collections.sort(intervals, (i1, i2) -> i1.start == i2.start ?
                                    Integer.compare(i1.end, i2.end) :
                                    Integer.compare(i1.start, i2.start));
        List<Interval> result = new ArrayList<>();
        for (Interval i : intervals) {
            if (result.isEmpty()) {
                result.add(i);
            } else {
                Interval last = result.get(result.size() - 1);
                if (last.end < i.start) {
                    result.add(i);
                } else {
                    result.set(result.size() - 1, merge(last, i));
                }
            }
        }
        return result;
    }
    
    private static Interval merge(Interval i1, Interval i2) {
        return new Interval(i1.start, Math.max(i1.end, i2.end));
    }
    
    public static void main(String[] args) {
        MergeIntervals mi = new MergeIntervals();
        mi.merge(Arrays.asList(new Interval(1, 3), new Interval(2, 6), new Interval(8, 10), new Interval(15, 18)));
    }

    public static class Interval {
        int start;
        int end;
        Interval() { start = 0; end = 0; }
        Interval(int s, int e) { start = s; end = e; }
    }

}
