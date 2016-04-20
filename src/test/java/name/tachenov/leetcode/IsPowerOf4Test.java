package name.tachenov.leetcode;

import java.util.Iterator;
import static org.assertj.core.api.Assertions.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/*
 * (C) Sergey A. Tachenov
 * This thing belongs to public domain. Really.
 */

/**
 * Problem 342 tests.
 * @author Sergey A. Tachenov
 */
public class IsPowerOf4Test {
    
    @DataProvider
    public Iterator<Object[]> powersOf4() {
        return new Iterator<Object[]>() {
            private int power = 1;
            private boolean hasNext = true;
            
            @Override
            public boolean hasNext() {
                return hasNext;
            }

            @Override
            public Object[] next() {
                int p = power;
                power *= 4;
                if (power < p) {
                    hasNext = false;
                }
                return new Object[] {p};
            }
        };
    }
    
    @Test(dataProvider = "powersOf4")
    public void testPowersOf4(int power) {
        assertThat(new IsPowerOf4().isPowerOfFour(power)).isTrue();
    }
    
    @DataProvider
    public Object[][] notPowersOf4() {
        return new Object[][] {
            {0}, {2}, {8}, {5}, {Integer.MIN_VALUE}
        };
    }
    
    @Test(dataProvider = "notPowersOf4")
    public void testNotPowersOf4(int notPower) {
        assertThat(new IsPowerOf4().isPowerOfFour(notPower)).as(notPower + " is not power of 4").isFalse();
    }
    
}
