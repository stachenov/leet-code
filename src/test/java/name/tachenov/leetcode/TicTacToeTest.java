/*
 * (C) Sergey A. Tachenov
 * This thing belongs to public domain. Really.
 */
package name.tachenov.leetcode;

import static org.assertj.core.api.Assertions.assertThat;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 *
 * @author Sergey A. Tachenov
 */
public class TicTacToeTest {
    
    @DataProvider(name = "moves")
    public Object[][] moves() {
        return new Object[][] {
            // x   o
            // o o
            // x x x
            {3, new int[][] {{0,0,1,0},{0,2,2,0},{2,2,1,0},{1,1,2,0},{2,0,1,0},{1,0,2,0},{2,1,1,1}}},
            // x   o
            // x o o
            // x   x
            {3, new int[][] {{0,0,1,0},{0,2,2,0},{2,2,1,0},{1,1,2,0},{2,0,1,0},{1,2,2,0},{1,0,1,1}}},
            // x   o
            //   o
            // o x x
            {3, new int[][] {{0,0,1,0},{0,2,2,0},{2,2,1,0},{1,1,2,0},{2,1,1,0},{2,0,2,2}}},
            // x   o
            //   x o
            //     x
            {3, new int[][] {{0,0,1,0},{0,2,2,0},{2,2,1,0},{1,2,2,0},{1,1,1,1}}},
        };
    }
    
    @Test(dataProvider = "moves")
    public void testSomeMethod(int size, int[][] moves) {
        TicTacToe ttt = new TicTacToe(size);
        for (int i = 0; i < moves.length; ++i) {
            int[] move = moves[i];
            int wins = ttt.move(move[0], move[1], move[2]);
            assertThat(wins).as("turn " + i).isEqualTo(move[3]);
        }
    }
    
}
