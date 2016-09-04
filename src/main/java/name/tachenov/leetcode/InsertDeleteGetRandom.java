/*
 * (C) Sergey A. Tachenov
 * This thing belongs to public domain. Really.
 */
package name.tachenov.leetcode;

import java.util.*;

public class InsertDeleteGetRandom {
    
    private final List<Value> values = new ArrayList<>();
    private final Map<Value, Integer> indexes = new HashMap<>();
    private final Map<Integer, Integer> counts = new HashMap<>();
    private final Random random = new Random();
    
    public boolean insert(int val) {
        int count = counts.getOrDefault(val, 0);
        Value newValue = new Value(val, count);
        counts.put(val, count + 1);
        values.add(newValue);
        indexes.put(newValue, values.size() - 1);
        return count == 0;
    }
    
    public boolean remove(int val) {
        int count = counts.getOrDefault(val, 0);
        if (count == 0)
            return false;
        Value value = new Value(val, count - 1);
        Integer index = indexes.get(value);
        Value last = values.get(values.size() - 1);
        values.set(index, last);
        indexes.put(last, index);
        values.remove(values.size() - 1);
        indexes.remove(value);
        if (count == 1)
            counts.remove(val);
        else
            counts.put(val, count - 1);
        return true;
    }
    
    public int getRandom() {
        return values.get(random.nextInt(values.size())).value;
    }
    
    private static class Value {
        private final int value;
        private final int number;

        public Value(int value, int number) {
            this.value = value;
            this.number = number;
        }

        @Override
        public int hashCode() {
            int hash = 3;
            hash = 13 * hash + this.value;
            hash = 13 * hash + this.number;
            return hash;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null || getClass() != obj.getClass())
                return false;
            final Value other = (Value) obj;
            return this.value == other.value
                    && this.number == other.number;
        }
    }
}
