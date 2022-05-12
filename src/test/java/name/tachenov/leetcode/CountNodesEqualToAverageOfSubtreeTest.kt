package name.tachenov.leetcode

import org.assertj.core.api.Assertions.*
import org.testng.annotations.*

class CountNodesEqualToAverageOfSubtreeTest {

    @Test
    fun test() {
        val root = TreeNode(4,
            TreeNode(8,
                TreeNode(0),
                TreeNode(1),
            ),
            TreeNode(5,
                null,
                TreeNode(6)
            )
        )
        assertThat(CountNodesEqualToAverageOfSubtree().averageOfSubtree(root)).isEqualTo(5)
    }

}
