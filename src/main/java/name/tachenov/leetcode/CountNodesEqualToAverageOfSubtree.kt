package name.tachenov.leetcode

// 2265. Count Nodes Equal to Average of Subtree

class CountNodesEqualToAverageOfSubtree {

    fun averageOfSubtree(root: TreeNode?): Int {
        return gatherDataFromSubtree(root).result
    }

    private fun gatherDataFromSubtree(node: TreeNode?): SubtreeData {
        if (node == null) {
            return SubtreeData(0, 0L, 0)
        }
        val leftData = gatherDataFromSubtree(node.left)
        val rightData = gatherDataFromSubtree(node.right)
        val thisSubtreeSum = leftData.sum + node.`val` + rightData.sum
        val thisSubtreeCount = leftData.count + 1 + rightData.count
        val thisSubtreeAverage = thisSubtreeSum / thisSubtreeCount
        val thisSubtreeMatches = if (thisSubtreeAverage == node.`val`.toLong()) 1 else 0
        return SubtreeData(leftData.result + thisSubtreeMatches + rightData.result, thisSubtreeSum, thisSubtreeCount)
    }

    private data class SubtreeData(
        val result: Int,
        val sum: Long,
        val count: Int,
    )

}
