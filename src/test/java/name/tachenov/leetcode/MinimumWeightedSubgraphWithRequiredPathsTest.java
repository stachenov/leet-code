package name.tachenov.leetcode;

import org.testng.annotations.*;

import static org.assertj.core.api.Assertions.*;

public class MinimumWeightedSubgraphWithRequiredPathsTest {
    @DataProvider
    public static Object[][] data() {
        return TestData.load("MinimumWeightedSubgraphWithRequiredPathsData.txt");
    }

    @Test(dataProvider = "data")
    public void test(int n, int[][] edges, int src1, int src2, int dest, long expected) {
        final var actual = new MinimumWeightedSubgraphWithRequiredPaths().minimumWeight(n, edges, src1, src2, dest);
        assertThat(actual).isEqualTo(expected);
    }
}
