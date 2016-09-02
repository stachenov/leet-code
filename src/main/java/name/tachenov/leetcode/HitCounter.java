/*
 * (C) Sergey A. Tachenov
 * This thing belongs to public domain. Really.
 */
package name.tachenov.leetcode;

import java.util.*;

/**
 *
 * @author Sergey A. Tachenov
 */
public class HitCounter {
    
    private final QueueWithCounts queue = new QueueWithCounts();
    
    /** Initialize your data structure here. */
    public HitCounter() {
        
    }
    
    /** Record a hit.
        @param timestamp - The current timestamp (in seconds granularity). */
    public void hit(int timestamp) {
        queue.cleanOld(timestamp, 300);
        queue.add(timestamp);
    }
    
    /** Return the number of hits in the past 5 minutes.
        @param timestamp - The current timestamp (in seconds granularity). */
    public int getHits(int timestamp) {
        queue.cleanOld(timestamp, 300);
        return queue.count;
    }

    private static class QueueWithCounts {
        
        private final Deque<TimestampWithCount> queue = new ArrayDeque<>();
        private int count;

        void cleanOld(int now, int interval) {
            while (!queue.isEmpty() && now - queue.peekFirst().timestamp >= interval) {
                count -= queue.removeFirst().count;
            }
        }

        void add(int timestamp) {
            if (queue.isEmpty() || queue.peekLast().timestamp != timestamp) {
                queue.add(new TimestampWithCount(timestamp));
            } else {
                ++queue.peekLast().count;
            }
            ++count;
        }
        
        private static class TimestampWithCount {
            final int timestamp;
            int count;

            public TimestampWithCount(int timestamp) {
                this.timestamp = timestamp;
                this.count = 1;
            }
        }
        
    }

}
