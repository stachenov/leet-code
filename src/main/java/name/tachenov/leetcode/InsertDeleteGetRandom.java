/*
 * (C) Sergey A. Tachenov
 * This thing belongs to public domain. Really.
 */
package name.tachenov.leetcode;

import java.util.*;

public class InsertDeleteGetRandom {
    
    private final List<Integer> values = new ArrayList<>();
    private final Map<Integer, Integer> indexes = new HashMap<>();
    private final Random random = new Random();
    
    public boolean insert(int val) {
        Integer index = indexes.get(val);
        if (index == null) {
            values.add(val);
            indexes.put(val, values.size() - 1);
            return true;
        } else {
            return false;
        }
    }
    
    public boolean remove(int val) {
        Integer index = indexes.get(val);
        if (index == null) {
            return false;
        } else {
            int last = values.get(values.size() - 1);
            values.set(index, last);
            indexes.put(last, index);
            values.remove(values.size() - 1);
            indexes.remove(val);
            return true;
        }
    }
    
    public int getRandom() {
        return values.get(random.nextInt(values.size()));
    }
}
