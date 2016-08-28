/*
 * (C) Sergey A. Tachenov
 * This thing belongs to public domain. Really.
 */
package name.tachenov.leetcode;

import java.util.*;
import java.util.stream.*;
import static org.assertj.core.api.Assertions.assertThat;
import org.testng.annotations.*;

/**
 *
 * @author Sergey A. Tachenov
 */
public class EliminationGameTest {
    
    @Test(dataProvider = "numbers")
    public void testSomeMethod(int n, int expected) {
        EliminationGame eg = new EliminationGame();
        System.out.println(expected);
        assertThat(eg.lastRemaining(n)).isEqualTo(expected);
    }
    
    @DataProvider
    public Object[][] numbers() {
        Object[][] params = new Object[100][];
        for (int i = 1; i <= params.length; ++i)
            params[i - 1] = new Object[] {i, lastRemaining(i)};
        return params;
    }
    
    public int lastRemaining(int n) {
        List<Integer> ll = new LinkedList<>();
        for (int i = 1; i <= n; ++i) {
            ll.add(i);
        }
        boolean ltr = true;
        while (ll.size() > 1) {
            ListIterator<Integer> it = ltr ? ll.listIterator() : ll.listIterator(ll.size());
            int step = 0;
            while (ltr ? it.hasNext() : it.hasPrevious()) {
                if (ltr)
                    it.next();
                else
                    it.previous();
                if (step % 2 == 0)
                    it.remove();
                ++step;
            }
            ltr = !ltr;
        }
        return ll.get(0);
    }
    
}
