/*
 * (C) Sergey A. Tachenov
 * This thing belongs to public domain. Really.
 */
package name.tachenov.leetcode;

import java.util.*;

public class LoggerRateLimiter {
    
    private final Map<String, Integer> lastTimestampOf = new HashMap<>();
    private int now;

    /** Initialize your data structure here. */
    public LoggerRateLimiter() {
        
    }
    
    /** Returns true if the message should be printed in the given timestamp, otherwise returns false.
        If this method returns false, the message will not be printed.
        The timestamp is in seconds granularity. */
    public boolean shouldPrintMessage(int timestamp, String message) {
        now = timestamp;
        if (notPrintedInLast10Seconds(message)) {
            lastTimestampOf.put(message, timestamp);
            return true;
        } else {
            return false;
        }
    }

    private boolean notPrintedInLast10Seconds(String message) {
        Integer last = lastTimestampOf.get(message);
        return last == null || now - last >= 10;
    }

}
