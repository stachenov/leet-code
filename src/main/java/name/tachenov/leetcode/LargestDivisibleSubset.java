/*
 * (C) Sergey A. Tachenov
 * This thing belongs to public domain. Really.
 */
package name.tachenov.leetcode;

import java.util.*;
import java.util.stream.*;

public class LargestDivisibleSubset {

    public List<Integer> largestDivisibleSubset(int[] nums) {
        Graph graph = new Graph(nums);
        List<Integer> indexes = graph.longestPath();
        return indexes.stream()
                .map(i -> nums[i])
                .collect(Collectors.toList());
    }

    private static boolean isDivisibleBy(int num1, int num2) {
        return num2 != 0 && num1 % num2 == 0;
    }

    private static class Graph {
        private final List<List<Integer>> outgoing = new ArrayList<>();
        private final List<List<Integer>> incoming = new ArrayList<>();

        public Graph(int[] nums) {
            for (int i = 0; i < nums.length; ++i) {
                outgoing.add(new ArrayList<>());
                incoming.add(new ArrayList<>());
            }
            for (int i = 0; i < nums.length; ++i) {
                for (int j = 0; j < nums.length; ++j) {
                    if (i != j && isDivisibleBy(nums[i], nums[j])) {
                        addEdge(i, j);
                    }
                }
            }
        }

        private void addEdge(int i, int j) {
            outgoing.get(i).add(j);
            incoming.get(j).add(i);
        }

        private List<Integer> sortTopologically() {
            List<List<Integer>> incomingCopy = copyOf(incoming);
            List<List<Integer>> outgoingCopy = copyOf(outgoing);
            List<Integer> sorted = new ArrayList<>();
            Queue<Integer> queue = new ArrayDeque<>();
            for (int i = 0; i < incoming.size(); ++i) {
                if (incoming.get(i).isEmpty())
                    queue.add(i);
            }
            while (!queue.isEmpty()) {
                int i = queue.remove();
                sorted.add(i);
                for (int j : new ArrayList<>(outgoing.get(i))) {
                    removeEdge(i, j);
                    if (incoming.get(j).isEmpty())
                        queue.add(j);
                }
            }
            restore(incomingCopy, incoming);
            restore(outgoingCopy, outgoing);
            return sorted;
        }

        private static List<List<Integer>> copyOf(List<List<Integer>> lists) {
            List<List<Integer>> copy = new ArrayList<>(lists.size());
            for (List<Integer> original : lists)
                copy.add(new ArrayList<>(original));
            return copy;
        }

        private void removeEdge(int i, int j) {
            outgoing.get(i).remove(Integer.valueOf(j));
            incoming.get(j).remove(Integer.valueOf(i));
        }

        private void restore(List<List<Integer>> from, List<List<Integer>> to) {
            for (int i = 0; i < to.size(); ++i)
                to.set(i, from.get(i));
        }

        List<Integer> longestPath() {
            List<Integer> indexes = sortTopologically();
            int[] longestTo = new int[size()];
            int iLongest = -1, lenLongest = -1;
            for (int j : indexes) {
                for (int i : incoming.get(j)) {
                    longestTo[j] = Math.max(longestTo[j], longestTo[i] + 1);
                }
                if (longestTo[j] > lenLongest) {
                    iLongest = j;
                    lenLongest = longestTo[j];
                }
            }
            List<Integer> result = new ArrayList<>();
            for (int j = iLongest; j != -1; ) {
                result.add(j);
                int nextJ = -1;
                int nextLen = -1;
                for (int i : incoming.get(j)) {
                    if (longestTo[i] > nextLen) {
                        nextJ = i;
                        nextLen = longestTo[i];
                    }
                }
                j = nextJ;
            }
            return result;
        }

        private int size() {
            return incoming.size();
        }
    }
}
