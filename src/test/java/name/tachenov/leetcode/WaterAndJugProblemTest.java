/*
 * (C) Sergey A. Tachenov
 * This thing belongs to public domain. Really.
 */
package name.tachenov.leetcode;

import static org.assertj.core.api.Assertions.*;
import org.testng.annotations.*;

public class WaterAndJugProblemTest {
    private static final String MEASURABLE_PROVIDER = "DP_MEASURABLE";
    private static final String NOT_MEASURABLE_PROVIDER = "DP_NOT_MEASURABLE";
    private WaterAndJugProblem solver = null;
    
    @BeforeMethod
    public void setUp() {
        solver = new WaterAndJugProblem();
    }
    
    @DataProvider(name = MEASURABLE_PROVIDER)
    public Object[][] measurableData() {
        return new Object[][] {
            {0, 0, 0},
            {0, 1, 1},
            {1, 0, 1},
            {1, 2, 3},
            {3, 5, 4},
            {2, 6, 4},
            {2, 6, 2},
            {2, 6, 6},
            {5, 17, 3},
            {2, 6, 8},
            {13, 11, 1},
        };
    }
    
    @Test(dataProvider = MEASURABLE_PROVIDER)
    public void measurable(int x, int y, int z) {
        boolean canMeasure = solver.canMeasureWater(x, y, z);
        assertThat(canMeasure).isTrue();
    }
    
    @DataProvider(name = NOT_MEASURABLE_PROVIDER)
    public Object[][] notMeasurableData() {
        return new Object[][] {
            {0, 0, 1},
            {2, 6, 5},
            {2, 6, 10},
        };
    }
    
    @Test(dataProvider = NOT_MEASURABLE_PROVIDER)
    public void notMeasurable(int x, int y, int z) {
        boolean canMeasure = solver.canMeasureWater(x, y, z);
        assertThat(canMeasure).isFalse();
    }
}
