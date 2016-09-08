/*
 * (C) Sergey A. Tachenov
 * This thing belongs to public domain. Really.
 */
package name.tachenov.leetcode;

import java.util.*;

public class LargestDivisibleSubset {

    public List<Integer> largestDivisibleSubset(int[] nums) {
        Arrays.sort(nums);
        Graph graph = new Graph(nums);
        List<Integer> indexes = graph.longestPath();
        List<Integer> subset = new ArrayList<>();
        for (int i : indexes)
            subset.add(nums[i]);
        return subset;
    }

    private static class Graph {
        private final List<List<Integer>> incoming = new ArrayList<>();

        public Graph(int[] nums) {
            for (int i = 0; i < nums.length; ++i) {
                incoming.add(new ArrayList<>());
            }
            for (int i = 0; i < nums.length; ++i) {
                for (int j = i + 1; j < nums.length; ++j) {
                    if (isDivisibleBy(nums[j], nums[i])) {
                        incoming(j).add(i);
                    }
                }
            }
        }

        private static boolean isDivisibleBy(int num1, int num2) {
            return num2 != 0 && num1 % num2 == 0;
        }

        private List<Integer> incoming(int j) {
            return incoming.get(j);
        }

        List<Integer> longestPath() {
            LongestPathFinder finder = new LongestPathFinder();
            return finder.findLongestPath();
        }

        private int size() {
            return incoming.size();
        }

        private class LongestPathFinder {

            private final int[] lenOfPathTo;
            private final int[] parent;
            private int vertexWithLongestPath;

            public LongestPathFinder() {
                lenOfPathTo = new int[size()];
                parent = new int[size()];
                calculateLongestPathsToVertices();
            }

            private void calculateLongestPathsToVertices() {
                Arrays.fill(parent, -1);
                vertexWithLongestPath = -1;
                int lenLongest = -1;
                for (int j = 0; j < size(); ++j) {
                    for (int i : incoming(j)) {
                        if (lenOfPathTo[i] + 1 > lenOfPathTo[j]) {
                            lenOfPathTo[j] = lenOfPathTo[i] + 1;
                            parent[j] = i;
                        }
                    }
                    if (lenOfPathTo[j] > lenLongest) {
                        vertexWithLongestPath = j;
                        lenLongest = lenOfPathTo[j];
                    }
                }
            }

            private List<Integer> findLongestPath() {
                List<Integer> result = new ArrayList<>();
                for (int j = vertexWithLongestPath; j != -1; j = parent[j]) {
                    result.add(j);
                }
                return result;
            }
        }
    }
}
