package name.tachenov.leetcode;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BinaryTreeCamerasTest {
    @DataProvider
    public static Object[][] data() {
        return new Object[][] {
                {null, 0},
                {new TreeNode(0), 1},
                {new TreeNode(0, new TreeNode(1), null), 1},
                {new TreeNode(0, new TreeNode(1), new TreeNode(2)), 1},
                {new TreeNode(0, null, new TreeNode(1)), 1},
                {new TreeNode(0, new TreeNode(1, new TreeNode(2), null), null), 1},
                {new TreeNode(0, new TreeNode(1, null, new TreeNode(2)), null), 1},
                {new TreeNode(0, new TreeNode(1, new TreeNode(2), new TreeNode(3)), null), 1},
                {new TreeNode(0,
                        new TreeNode(1,
                                new TreeNode(2,
                                        new TreeNode(3), new TreeNode(4)),
                                        null),
                                null),
                        2},
                {new TreeNode(0,
                        new TreeNode(1,
                                new TreeNode(2,
                                        new TreeNode(3,
                                            new TreeNode(4), new TreeNode(5)),
                                        null),
                                null),
                        null),
                        2},
                {new TreeNode(0,
                        new TreeNode(1,
                                new TreeNode(2,
                                        new TreeNode(3,
                                                new TreeNode(4,
                                                    new TreeNode(5), new TreeNode(6)),
                                                null),
                                        null),
                                null),
                        null),
                        2},
                {new TreeNode(0,
                        new TreeNode(1,
                                new TreeNode(2,
                                        new TreeNode(3,
                                                new TreeNode(4,
                                                        new TreeNode(5,
                                                            new TreeNode(5), new TreeNode(6)),
                                                        null),
                                                null),
                                        null),
                                null),
                        null),
                        3},
                {new TreeNode(0, null, new TreeNode(1, new TreeNode(2), null)), 1},
                {new TreeNode(0, null, new TreeNode(1, null, new TreeNode(2))), 1},
                {new TreeNode(0,
                        new TreeNode(1,
                                new TreeNode(2,
                                        new TreeNode(3),
                                        null),
                                null),
                        null),
                        2},
                {new TreeNode(0,
                        null,
                        new TreeNode(1,
                                null,
                                new TreeNode(2,
                                        null,
                                        new TreeNode(3)
                                ))
                        ),
                        2},
                {new TreeNode(0,
                        new TreeNode(1,
                                new TreeNode(2,
                                        new TreeNode(3),
                                        new TreeNode(4)),
                                new TreeNode(5)),
                        new TreeNode(6,
                                new TreeNode(7,
                                        new TreeNode(8),
                                        new TreeNode(9)),
                                new TreeNode(10))
                        ),
                        4},
        };
    }

    @Test(dataProvider = "data")
    public void minCameraCover(TreeNode root, int expected) {
        assertThat(new BinaryTreeCameras().minCameraCover(root)).isEqualTo(expected);
    }
}
