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
        return appendNewValue(val).wasAddedFirst();
    }

    private Value appendNewValue(int val) {
        Value newValue = new Value(val, counts.getOrDefault(val, 0));
        values.add(newValue);
        indexes.put(newValue, values.size() - 1);
        counts.put(val, newValue.number + 1);
        return newValue;
    }
    
    public boolean remove(int val) {
        int count = counts.getOrDefault(val, 0);
        if (count == 0)
            return false;
        int index = indexes.get(new Value(val, count - 1));
        swapWithLastValue(index);
        removeLastValue();
        return true;
    }

    private void swapWithLastValue(int index) {
        int lastIndex = values.size() - 1;
        Value temp = values.get(index);
        Value last = values.get(lastIndex);
        values.set(index, last);
        values.set(lastIndex, temp);
        indexes.put(last, index);
        indexes.put(temp, lastIndex);
    }

    private void removeLastValue() {
        int lastIndex = values.size() - 1;
        Value value = values.get(lastIndex);
        values.remove(lastIndex);
        indexes.remove(value);
        if (value.wasAddedFirst())
            counts.remove(value.value);
        else
            counts.put(value.value, value.number);
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

        boolean wasAddedFirst() {
            return number == 0;
        }
    }
}
