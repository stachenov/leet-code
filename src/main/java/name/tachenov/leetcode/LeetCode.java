/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package name.tachenov.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author alqualos
 */
public class LeetCode {
    public static int[][] array2d(String s) {
        List<List<Integer>> result = null;
        int depth = 0;
        StringTokenizer st = new StringTokenizer(s, "[], \n\r\t", true);
        List<Integer> current = null;
        OUTER:
        while (st.hasMoreTokens()) {
            String t = st.nextToken();
            if (t.trim().isEmpty())
                continue;
            switch (t) {
                case "[":
                    ++depth;
                    if (depth == 1) {
                        result = new ArrayList<>();
                    } else if (depth == 2) {
                        current = new ArrayList<>();
                    } else {
                        throw new IllegalArgumentException("Too deep");
                    }
                    break;
                case "]":
                    --depth;
                    if (depth == 1) {
                        assert result != null;
                        result.add(current);
                        current = null;
                    } else if (depth == 0) {
                        break OUTER;
                    } else {
                        throw new IllegalArgumentException("Unbalanced ]");
                    }
                    break;
                case ",":
                    break;
                default:
                    if (current == null) {
                        throw new IllegalArgumentException("Number outside []");
                    }
                    current.add(Integer.parseInt(t));
                    break;
            }
        }
        if (result == null) {
            return new int[0][0];
        }
        final List<int[]> list = result.stream()
                .map(l -> l.stream().mapToInt(Integer::intValue).toArray())
                .collect(Collectors.toList());
        return list.toArray(new int[list.size()][]);
    }
    
    public static TreeNode buildBBST(Random rnd, int n) {
        int[] values = new int[n];
        for (int i = 0; i < n; ++i) {
            values[i] = rnd.nextInt(n);
        }
        Arrays.sort(values);
        return buildBBST(values, 0, n);
    }

    private static TreeNode buildBBST(int[] values, int start, int end) {
        if (start == end)
            return null;
        int mid = (start + end - 1) / 2;
        TreeNode root = new TreeNode(values[mid]);
        root.left = buildBBST(values, start, mid);
        root.right = buildBBST(values, mid + 1, end);
        return root;
    }
    
    public static int[] array1d(String s) {
        return Stream.of(s.split(",")).mapToInt(Integer::parseInt).toArray();
    }
}
