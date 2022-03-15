/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package name.tachenov.leetcode;

import java.util.*;
import java.util.stream.*;

/**
 *
 * @author alqualos
 */
public class MissingNumber {
    
    private final Deque<Node> stack = new LinkedList<>();
    private Node[] nodes = null;
    private int index;

    private boolean strongConnect(int n, List<Integer>[] descendants) {
        Node node = new Node(index++);
        nodes[n] = node;
        stack.push(node);
        node.onStack = true;
        List<Integer> desc = descendants[n];
        if (desc != null) {
            for (int d : desc) {
                if (nodes[d] == null) {
                    if (strongConnect(d, descendants))
                        return true;
                    node.lowLink = Math.min(node.lowLink, nodes[d].lowLink);
                } else if (nodes[d].onStack) {
                    node.lowLink = Math.min(node.lowLink, nodes[d].lowLink);
                }
            }
        }
        if (node.lowLink == node.index) { // root node
            int count = 0;
            Node connected;
            do {
                connected = stack.pop();
                connected.onStack = false;
                ++count;
            } while (connected != node);
            System.out.println(count);
            if (count > 1) {
                return true; // loop detected
            }
        }
        return false;
    }
    
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<Integer>[] descendants = new List[numCourses];
        for (int i = 0; i < prerequisites.length; ++i) {
            int[] req = prerequisites[i];
            if (req[0] == req[1])
                return false;
            List<Integer> desc = descendants[req[0]];
            if (desc == null) {
                desc = new ArrayList<>();
                descendants[req[0]] = desc;
            }
            desc.add(req[1]);
        }
        nodes = new Node[numCourses];
        stack.clear();
        index = 0;
        try {
            for (int i = 0; i < numCourses; ++i) {
                if (nodes[i] == null && strongConnect(i, descendants))
                    return false;
            }
            return true;
        } finally {
            stack.clear();
            nodes = null;
        }
    }
    
    private static class Node {
        public int index;
        public int lowLink;
        public boolean onStack;
        public Node(int index) {
            this.index = index;
            this.lowLink = index;
        }
    }
    
    public static int missingNumber(int[] nums) {
        if (nums.length == 0)
            return 0;
        // max number of hex digits:
        final int hd = (32 - Integer.numberOfLeadingZeros(nums.length) + 3) / 4;
        int result = 0;
        for (int d = 0; d < hd; ++d) {
            int[] distr = new int[16];
            for (int num : nums) {
                ++distr[(num >>> (d * 4)) & 0xF];
            }
            int[] expected = new int[16];
            for (int num = 0; num <= nums.length; ++num) {
                ++expected[(num >>> (d * 4)) & 0xF];
            }
            for (int i = 0; i < 16; ++i) {
                if (distr[i] != expected[i]) {
                    result |= i << (d * 4);
                }
            }
        }
        return result;
    }
    
    public static int missingNumberXor(int[] nums) {
        int res = 0;
        for (int i = 1; i <= nums.length; i++) {
            res ^= i ^ nums[i - 1];
        }
        return res;
    }
    public static void main(String[] args) {
        Stream<Integer> str = Stream.of(11, 12, 22);
        Map<Integer, Integer> lastDigits = new HashMap<>();
        str.forEach(i -> {
            int ld = i % 10;
            if (ld == 2)
                lastDigits.put(i, ld);
        });
        System.out.println();
    }
}
