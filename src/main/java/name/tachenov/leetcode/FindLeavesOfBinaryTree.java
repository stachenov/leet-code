/*
 * (C) Sergey A. Tachenov
 * This thing belongs to public domain. Really.
 */
package name.tachenov.leetcode;

import java.util.*;

public class FindLeavesOfBinaryTree {
    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> leaves = new ArrayList<>();
        Map<TreeNode, Integer> depths = new HashMap<>();
        int depth = findDepths(root, depths);
        for (int i = 0; i <= depth; ++i)
            leaves.add(new ArrayList<>());
        findLeaves(root, depths, leaves);
        return leaves;
    }

    private static int findDepths(TreeNode root, Map<TreeNode, Integer> depths) {
        if (root == null)
            return -1;
        int depth = 1 + Math.max(findDepths(root.left, depths), findDepths(root.right, depths));
        depths.put(root, depth);
        return depth;
    }

    private void findLeaves(TreeNode root, Map<TreeNode, Integer> depths, List<List<Integer>> leaves) {
        if (root == null)
            return;
        leaves.get(depths.get(root)).add(root.val);
        findLeaves(root.left, depths, leaves);
        findLeaves(root.right, depths, leaves);
    }
}
