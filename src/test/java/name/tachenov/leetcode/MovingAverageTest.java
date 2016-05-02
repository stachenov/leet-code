/*
 * (C) Sergey A. Tachenov
 * This thing belongs to public domain. Really.
 */
package name.tachenov.leetcode;

import static org.assertj.core.api.Assertions.*;
import org.testng.annotations.Test;

public class MovingAverageTest {
    
    private static final double TOLERANCE = 0.001;
    
    @Test
    public void average() {
        MovingAverage avg = new MovingAverage(3);
        assertThat(avg.next(1)).isCloseTo(1.0, offset(TOLERANCE));
        assertThat(avg.next(2)).isCloseTo(1.5, offset(TOLERANCE));
        assertThat(avg.next(3)).isCloseTo(2.0, offset(TOLERANCE));
        assertThat(avg.next(4)).isCloseTo(3.0, offset(TOLERANCE));
    }
    
}
