/*
 * (C) Sergey A. Tachenov
 * This thing belongs to public domain. Really.
 */
package name.tachenov.leetcode;

import static org.assertj.core.api.Assertions.assertThat;
import org.testng.annotations.*;

public class SnakeGameTest {
    
    @Test(dataProvider = "moves")
    public void move(int width, int height, int[][] food, String[] moves, int[] expectedScore) {
        SnakeGame game = new SnakeGame(width, height, food);
        for (int i = 0; i < moves.length; ++i) {
            assertThat(game.move(moves[i])).isEqualTo(expectedScore[i]);
        }
    }
    
    @DataProvider
    public Object[][] moves() {
        return new Object[][] {
            {1, 1, new int[0][0], new String[] {"U"}, new int[] {-1}},
            {1, 1, new int[0][0], new String[] {"D"}, new int[] {-1}},
            {1, 1, new int[0][0], new String[] {"R"}, new int[] {-1}},
            {1, 1, new int[0][0], new String[] {"L"}, new int[] {-1}},
            {1, 2, new int[0][0], new String[] {"R"}, new int[] {-1}},
            {1, 2, new int[0][0], new String[] {"L"}, new int[] {-1}},
            {1, 2, new int[0][0], new String[] {"U"}, new int[] {-1}},
            {1, 2, new int[0][0], new String[] {"D"}, new int[] {0}},
            {2, 1, new int[0][0], new String[] {"R"}, new int[] {0}},
            {2, 1, new int[0][0], new String[] {"L"}, new int[] {-1}},
            {2, 1, new int[0][0], new String[] {"U"}, new int[] {-1}},
            {2, 1, new int[0][0], new String[] {"D"}, new int[] {-1}},
            {1, 2, new int[0][0], new String[] {"D", "U"}, new int[] {0, 0}},
            {2, 1, new int[0][0], new String[] {"R", "L"}, new int[] {0, 0}},
            {2, 2, new int[0][0], new String[] {"R", "D", "L", "U", "U"}, new int[] {0, 0, 0, 0, -1}},
            {1, 2, new int[][] {{1, 0}}, new String[] {"D"}, new int[] {1}},
            {1, 2, new int[][] {{1, 0}}, new String[] {"D", "U"}, new int[] {1, 1}},
            {2, 2, new int[][] {{1, 0}, {1, 1}, {0, 1}}, new String[] {"D", "R", "U"}, new int[] {1, 2, 3}},
            {2, 3, new int[][] {{1, 0}, {2, 0}, {2, 1}, {1, 1}}, new String[] {"D", "D", "R", "U"}, new int[] {1, 2, 3, 4, -1}},
            {2, 2, new int[][] {{1, 1}, {1, 0}}, new String[] {"D", "R"}, new int[] {0, 1}},
            {3, 2, new int[][] {{1, 2}, {0, 1}}, new String[] {"R", "D", "R", "U", "L", "U"}, new int[] {0, 0, 1, 1, 2, -1}},
        };
    }
    
}
