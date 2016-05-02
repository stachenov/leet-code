/*
 * (C) Sergey A. Tachenov
 * This thing belongs to public domain. Really.
 */
package name.tachenov.leetcode;

/**
 * Moving Average from Data Stream (#346).
 * @author Sergey A. Tachenov
 */
public class MovingAverage {
    
    private final int[] buffer;
    private int index = 0;
    private int count = 0;
    private double sum = 0.0f;

    public MovingAverage(int size) {
        buffer = new int[size];
    }
    
    public double next(int value) {
        if (count == buffer.length) {
            sum -= buffer[index];
        } else {
            ++count;
        }
        buffer[index++] = value;
        if (index == buffer.length) {
            index = 0;
        }
        sum += value;
        return sum / count;
    }
    
}
