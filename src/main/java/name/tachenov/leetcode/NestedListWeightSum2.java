/*
 * (C) Sergey A. Tachenov
 * This thing belongs to public domain. Really.
 */
package name.tachenov.leetcode;

import java.util.*;

public class NestedListWeightSum2 {

    public static int depthSumInverse(List<NestedInteger> nestedList) {
        int depth = 0;
        for (NestedInteger ni : nestedList) {
            depth = Math.max(depth, depth(ni));
        }
        int sum = 0;
        for (NestedInteger ni : nestedList) {
            sum += depthSumInverse(ni, depth);
        }
        return sum;
    }

    public static int depthSumInverse(NestedInteger nestedList, int depth) {
        if (nestedList.isInteger()) {
            return depth * nestedList.getInteger();
        } else {
            int sumNested = 0;
            for (NestedInteger nested : nestedList.getList()) {
                sumNested += depthSumInverse(nested, depth - 1);
            }
            return sumNested;
        }
    }

    private static int depth(NestedInteger nestedList) {
        if (nestedList.isInteger()) {
            return 1;
        } else {
            int depth = 0;
            for (NestedInteger nested : nestedList.getList()) {
                depth = Math.max(depth, 1 + depth(nested));
            }
            return depth;
        }
    }

    public static class NestedInteger {
        
        private final List<NestedInteger> nested;
        private Integer value;
        
        NestedInteger() {
            value = null;
            nested = new ArrayList<>();
        }
        
        NestedInteger(int value) {
            this.value = value;
            nested = null;
        }
        
        NestedInteger(List<NestedInteger> nested) {
            this.nested = nested;
        }

        boolean isInteger() {
            return value != null;
        }

        Integer getInteger() {
            return value;
        }

        void setInteger(int value) {
            this.value = value;
        }

        void add(NestedInteger ni) {
            nested.add(ni);
        }

        List<NestedInteger> getList() {
            return nested;
        }
    }
}

