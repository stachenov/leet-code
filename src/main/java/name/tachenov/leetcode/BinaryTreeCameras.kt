package name.tachenov.leetcode

class BinaryTreeCameras {
    fun minCameraCover(root: TreeNode?): Int {
        return minCameraCoverRNAC(root)
    }

    // This solution is pretty naive, but efficient due to memoization.
    // We have three basic cases for any subtree:
    // RNAC - Root Not Already Covered
    // RAC - Root Already Covered
    // RMHC - Root Must Have Camera
    // We start with the RNAC status. To cover the root we can set up a camera:
    // - at the root itself;
    // - at the left child (if any);
    // - at the right child (if any).
    // The minimal result of these three is the answer.
    // In the first case, both children go down the RAC branch.
    // In the second case, the left child goes down the RMHC branch,
    // the right one goes along the RNAC branch. The third case is symmetric.

    private fun minCameraCoverRNAC(root: TreeNode?): Int {
        if (root == null)
            return 0
        return rnacMemo.getOrPut(root) {
            var result = Int.MAX_VALUE
            result = minOf(result, 1 + minCameraCoverRAC(root.left) + minCameraCoverRAC(root.right))
            if (root.left != null)
                result = minOf(result, minCameraCoverRMHC(root.left) + minCameraCoverRNAC(root.right))
            if (root.right != null)
                result = minOf(result, minCameraCoverRNAC(root.left) + minCameraCoverRMHC(root.right))
//        System.out.printf("%d: RNAC %d%n", root.`val`, result)
            result
        }
    }

    private val rnacMemo = HashMap<TreeNode, Int>()

    // In the RAC branch, we need to consider two cases:
    // - either we set up a camera at the root anyway;
    // - or we don't.
    // In the first case, the children go RAC, otherwise RNAC.

    private fun minCameraCoverRAC(root: TreeNode?): Int {
        if (root == null)
            return 0
        return racMemo.getOrPut(root) {
            var result = Int.MAX_VALUE
            result = minOf(result, 1 + minCameraCoverRAC(root.left) + minCameraCoverRAC(root.right))
            result = minOf(result, minCameraCoverRNAC(root.left) + minCameraCoverRNAC(root.right))
//        System.out.printf("%d: RAC %d%n", root.`val`, result)
            result
        }
    }

    private val racMemo = HashMap<TreeNode, Int>()

    // And finally, the RMHC branch. Simply set up a camera and go RAC for both children.

    private fun minCameraCoverRMHC(root: TreeNode?): Int {
        root!!
        return rmhcMemo.getOrPut(root) {
            var result = Int.MAX_VALUE // not needed except for consistent style
            result = minOf(result, 1 + minCameraCoverRAC(root.left) + minCameraCoverRAC(root.right))
//        System.out.printf("%d: RMHC %d%n", root.`val`, result)
            result
        }
    }

    private val rmhcMemo = HashMap<TreeNode, Int>()
}
