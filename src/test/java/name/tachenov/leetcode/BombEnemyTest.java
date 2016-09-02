/*
 * (C) Sergey A. Tachenov
 * This thing belongs to public domain. Really.
 */
package name.tachenov.leetcode;

import static org.assertj.core.api.Assertions.*;
import org.testng.annotations.*;

public class BombEnemyTest {
    
    @Test(dataProvider = "grids")
    public void maxKilledEnemies(char[][] grid, int expected) {
        assertThat(new BombEnemy().maxKilledEnemies(grid)).isEqualTo(expected);
    }
    
    @DataProvider
    public Object[][] grids() {
        return new Object[][] {
            {new char[0][0], 0},
            {new char[][] {
                {'0', 'E', '0', '0'},
                {'E', '0', 'W', 'E'},
                {'0', 'E', '0', '0'},
                }, 3},
            {new char[][] {
                {'0', 'E'},
                {'E', '0'},
                {'0', 'E'},
                {'0', 'E'},
                }, 4},
            {new char[][] {
                {'0', 'E'},
                {'E', '0'},
                {'0', 'W'},
                {'0', 'E'},
                }, 2},
        };
    }
    
}
