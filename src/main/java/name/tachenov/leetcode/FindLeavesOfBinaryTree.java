/*
 * (C) Sergey A. Tachenov
 * This thing belongs to public domain. Really.
 */
package name.tachenov.leetcode;

import java.util.*;

public class FindLeavesOfBinaryTree {
    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> leaves = new ArrayList<>();
        findLeaves(root, leaves);
        return leaves;
    }

    private int findLeaves(TreeNode root, List<List<Integer>> leaves) {
        if (root == null)
            return -1;
        int depth = 1 + Math.max(findLeaves(root.left, leaves),
                                 findLeaves(root.right, leaves));
        while (depth >= leaves.size())
            leaves.add(new ArrayList<>());
        leaves.get(depth).add(root.val);
        return depth;
    }
}
