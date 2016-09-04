/*
 * (C) Sergey A. Tachenov
 * This thing belongs to public domain. Really.
 */
package name.tachenov.leetcode;

import java.util.*;
import static org.assertj.core.api.Assertions.*;
import org.testng.annotations.*;

public class FindLeavesOfBinaryTreeTest {
    
    @Test(dataProvider = "trees")
    public void findLeaves(TreeNode root, List<List<Integer>> expected) {
        FindLeavesOfBinaryTree flobt = new FindLeavesOfBinaryTree();
        assertThat(flobt.findLeaves(root)).isEqualTo(expected);
    }
    
    @DataProvider
    public Object[][] trees() {
        return new Object[][] {
            {null, Arrays.asList()},
            {new TreeNode(1), Arrays.asList(Arrays.asList(1))},
            {new TreeNode(1,
                    new TreeNode(2),
                    new TreeNode(3)),
                Arrays.asList(Arrays.asList(2, 3), Arrays.asList(1))},
            {new TreeNode(1,
                    new TreeNode(2,
                        new TreeNode(4),
                        new TreeNode(5)),
                    new TreeNode(3)),
                Arrays.asList(Arrays.asList(4, 5, 3), Arrays.asList(2), Arrays.asList(1))},
        };
    }
    
}
