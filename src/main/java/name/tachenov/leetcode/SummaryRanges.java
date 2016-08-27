/*
 * (C) Sergey A. Tachenov
 * This thing belongs to public domain. Really.
 */
package name.tachenov.leetcode;

import java.util.*;

public class SummaryRanges {

    private final List<Interval> intervals = new ArrayList<>();

    public SummaryRanges() {
    }
    
    public void addNum(int val) {
        if (intervals.isEmpty()) {
            intervals.add(new Interval(val, val));
            return;
        }
        int pos = findInsertionPoint(val);
        if (pos == intervals.size()) {
            insertAfterLast(val);
        } else if (pos == 0) {
            insertBeforeFirst(val);
        } else {
            insertIntoMiddle(pos, val);
        }
    }

    private int findInsertionPoint(int val) {
        int left = 0, right = intervals.size() - 1;
        while (left <= right) {
            int mid = (left + right) >>> 1;
            Interval midInterval = intervals.get(mid);
            if (val >= midInterval.start && val <= midInterval.end) {
                return mid;
            } else if (val > midInterval.end) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    private void insertAfterLast(int val) {
        Interval last = intervals.get(intervals.size() - 1);
        if (val == last.end + 1) {
            last.end = val;
        } else {
            intervals.add(new Interval(val, val));
        }
    }

    private void insertBeforeFirst(int val) {
        Interval first = intervals.get(0);
        if (val == first.start - 1) {
            first.start = val;
        } else if (val < first.start) {
            intervals.add(0, new Interval(val, val));
        }
    }

    private void insertIntoMiddle(int pos, int val) {
        Interval next = intervals.get(pos);
        Interval prev = intervals.get(pos - 1);
        if (val == prev.end + 1 && val == next.start - 1) {
            intervals.remove(pos);
            prev.end = next.end;
        } else if (val == prev.end + 1) {
            prev.end = val;
        } else if (val == next.start - 1) {
            next.start = val;
        } else if (val > prev.end && val < next.start) {
            intervals.add(pos, new Interval(val, val));
        }
    }
    
    public List<Interval> getIntervals() {
        return intervals;
    }
}
class Interval {
    int start;
    int end;
    Interval() { start = 0; end = 0; }
    Interval(int s, int e) { start = s; end = e; }

    @Override
    public String toString() {
        return "Interval{" + "start=" + start + ", end=" + end + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 19 * hash + this.start;
        hash = 19 * hash + this.end;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Interval other = (Interval) obj;
        if (this.start != other.start) {
            return false;
        }
        if (this.end != other.end) {
            return false;
        }
        return true;
    }
}
