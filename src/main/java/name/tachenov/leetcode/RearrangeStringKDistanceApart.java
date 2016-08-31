/*
 * (C) Sergey A. Tachenov
 * This thing belongs to public domain. Really.
 */
package name.tachenov.leetcode;

import java.util.*;

public class RearrangeStringKDistanceApart {
    
    private static final Comparator<CountedChar> COUNT_DESC
            = (c1, c2) -> Integer.compare(c2.count, c1.count);
    private int k;

    public String rearrangeString(String str, int k) {
        this.k = k;
        if (k <= 1)
            return str;
        Map<Integer, Integer> freq = new HashMap<>();
        for (int c : str.codePoints().toArray()) {
            freq.put(c, freq.getOrDefault(c, 0) + 1);
        }
        PriorityQueue<CountedChar> maxHeap = new PriorityQueue<>(COUNT_DESC);
        for (Map.Entry<Integer, Integer> e : freq.entrySet()) {
            maxHeap.add(new CountedChar(e.getKey(), e.getValue()));
        }
        StringBuilder sb = new StringBuilder();
        int pos = 0;
        Queue<CountedChar> cooldown = new ArrayDeque<>();
        while (!maxHeap.isEmpty()) {
            CountedChar cc = maxHeap.remove();
            sb.appendCodePoint(cc.codePoint);
            cc.lastPosition = pos;
            if (--cc.count > 0) {
                cooldown.add(cc);
            }
            ++pos;
            if (!cooldown.isEmpty() && cooldown.peek().allowedAt(pos)) {
                maxHeap.add(cooldown.remove());
            }
        }
        return cooldown.isEmpty() ? sb.toString() : "";
    }
    
    private class CountedChar {
        final int codePoint;
        int count;
        int lastPosition;

        public CountedChar(int codePoint, int count) {
            this.codePoint = codePoint;
            this.count = count;
            this.lastPosition = Integer.MIN_VALUE;
        }

        boolean allowedAt(int pos) {
            return pos >= lastPosition + k;
        }
    }

}
